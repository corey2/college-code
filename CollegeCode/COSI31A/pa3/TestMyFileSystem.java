package pa3;

import java.io.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;
import static org.junit.Assert.*;

/**
 * Test MyFileSystem, including some internal implementation details
 * related to how SuperBlock and FreeMap work together.
 *
 * Normally, when you write a test suite, you would try to test
 * primarily the public interface. However, in this case it makes
 * sense to ensure that the file system is working correctly by
 * testing its internal state as well as its interface.
 *
 * To run, enter this at the command line:
 *
 * <pre>
 * javac TestMyFileSystem.java && \
 * java org.junit.runner.JUnitCore TestMyFileSystem
 * </pre>
 *
 * {@internal We can access disk, freeMap, etc. from MyFileSystem
 * because they are declared package-local instead of private.}
 */
@RunWith(Enclosed.class)
public class TestMyFileSystem {
    public static void deleteDisk() {
        new File("DISK").delete();
    }

    @BeforeClass
    public static void init() {
        Disk.printStats = false; // squelch output not needed during tests
    }

    public static class FormatAndReopening {
        private MyFileSystem fs;

        @BeforeClass
        public static void startFresh() {
            deleteDisk();
        }

        @Before
        public void setUp() throws IOException {
            fs = new MyFileSystem();
        }
        
        @After
        public void tearDown() throws IOException {
            fs.shutdown();
        }

        private void testSuperBlock() {
            assertEquals(fs.superBlock.size,    100); // size in # of blocks
            assertEquals(fs.superBlock.msize,   0);   // map fits in superblock
            assertEquals(fs.superBlock.isize,   5);   // 5 inode blocks
            assertEquals(fs.superBlock.dsize(), 94);  // size - isize - super
        }
        
        @Test
        public void testFormatDisk() throws IOException {
            fs.formatDisk(100, 5);
            testSuperBlock();
        }
        
        @Test
        public void testReopenFileSystem() {
            // should work even though we reopened fs and didn't reformat
            testSuperBlock();
        }
    }

    public static class FreeMap {
        private MyFileSystem fs;
        
        @Before
        public void setUp() throws IOException {
            deleteDisk();
            fs = new MyFileSystem();
            fs.formatDisk(100, 5);
        }
        
        @After
        public void tearDown() throws IOException {
            fs.shutdown();
        }
        
        @Test
        public void testFreeMapFind() {
            assertEquals(6, fs.freeMap.find());     // after super and inodes
            assertEquals(7, fs.freeMap.find());     // 6th was just reserved
            fs.freeMap.clear(6);                    // 6th block made free again
            assertEquals(6, fs.freeMap.find());     // because 6th was made free
            assertEquals(8, fs.freeMap.find());     // 7th was still reserved
            for(int i = 9; i < 100; ++i)            // reserve rest of blocks
                assertEquals(i, fs.freeMap.find()); // finding in order
            assertEquals(0, fs.freeMap.find());     // no more free blocks
            fs.freeMap.clear(42);                   // clear another block
            assertEquals(42, fs.freeMap.find());    // block we just cleared
        }
    }

    public static class FileSystemInterface {
        private MyFileSystem fs;
        
        @Before
        public void setUp() throws IOException {
            deleteDisk();
            fs = new MyFileSystem();
            fs.formatDisk(100, 2);
        }
        
        @After
        public void tearDown() throws IOException {
            fs.shutdown();
        }

        @Test
        public void testFileTable() {
            // Ensure that we can allocate and add MAX_FILES file descriptors
            for(int fd = 0; fd < FileTable.MAX_FILES; ++fd) {
                assertEquals(fd, fs.fileTable.allocate());
                assertEquals(0, fs.fileTable.add(new Inode(), fd + 1, fd));
            }
            // Ensure that -1 is returned when file table is full
            assertEquals(-1, fs.fileTable.allocate());

            // To avoid errors when shutting down, free everything
            // we allocated (since we didn't actually make any real
            // files that can be closed)
            for(int fd = 0; fd < FileTable.MAX_FILES; ++fd)
                fs.fileTable.free(fd);
        }

        @Test
        public void testCreate() throws IOException {
            int maxInumber = 2 * InodeBlock.COUNT;
            // assume maxInumber < FileTable.MAX_FILES
            for(int inumber = 1; inumber <= maxInumber; ++inumber) {
                int fd = fs.create();
                assertEquals(inumber, fs.fileTable.getInumber(fd));
            }
            assertEquals(-1, fs.create());
        }

        @Test
        public void testOpen() throws IOException {
            int fd = fs.create();
            assertEquals(0, fd);
            assertEquals(1, fs.fileTable.getInumber(fd));
            fs.shutdown();
            fs = new MyFileSystem();
            fd = fs.open(1);
            assertEquals(0, fd);
            assertEquals(1, fs.fileTable.getInumber(fd));
            assertEquals(-1, fs.open(2));
        }

        @Test
        public void testInumber() throws IOException {
            int fd = fs.create();
            assertEquals(1, fs.fileTable.getInumber(fd));
            assertEquals(1, fs.inumber(fd));
        }

        @Test
        public void testSeek() throws IOException {
            int fd = fs.create();
            assertEquals(0,  fs.fileTable.getSeekPointer(fd));
            assertEquals(10, fs.seek(fd, 10, Whence.SEEK_SET));
            assertEquals(10, fs.seek(fd, 10, Whence.SEEK_END));
            assertEquals(20, fs.seek(fd, 10, Whence.SEEK_CUR));
            assertEquals(0,  fs.seek(fd, 0,  Whence.SEEK_SET));

            // Hack to test SEEK_END with non-zero file size before we
            // test write.
            //
            fs.fileTable.getInode(fd).size = 42;
            assertEquals(50, fs.seek(fd, 8, Whence.SEEK_END));
            assertEquals(50, fs.seek(fd, 0, Whence.SEEK_CUR));
            assertEquals(50, fs.fileTable.getSeekPointer(fd));
        }

        @Test
        public void testClose() throws IOException {
            int fd = fs.create();
            assertEquals(0, fd);
            assertEquals(1, fs.fileTable.getInumber(fd));

            // Change size of file and write it out with close
            fs.fileTable.getInode(fd).size = 100;
            assertEquals(0, fs.close(fd)); // close should be successful

            // After closing the file, the fd should be reused
            fd = fs.create();
            assertEquals(1, fs.create());

            // Re-open the file, the size should still be 100
            fd = fs.open(1);
            assertEquals(2, fd);
            assertEquals(100, fs.fileTable.getInode(fd).size);
        }

        @Test
        public void testDelete() throws IOException {
            int fd = fs.create();
            int inumber = fs.inumber(fd);
            assertEquals(-1, fs.delete(inumber));
            assertEquals(0, fs.close(fd));
            fd = fs.open(inumber);
            assertEquals(0, fd);
            assertEquals(0, fs.close(fd));
            assertEquals(0, fs.delete(inumber));
            assertEquals(-1, fs.open(inumber));
        }

        private void testFileSize(int fd, int expectedSize) {
            assertEquals(expectedSize, fs.fileTable.getInode(fd).size);
        }

        @Test
        public void testWrite() throws IOException {
            int fd = fs.create();

            // Write 3 bytes and make sure space is allocated and
            // size is adjusted
            byte[] foo = { 'f', 'o', 'o' };
            assertEquals(3, fs.write(fd, foo));
            assertEquals(3, fs.seek(fd, 0, Whence.SEEK_CUR));
            assertEquals(4, fs.freeMap.find()); // block 3 should be used
            fs.freeMap.clear(4);
            testFileSize(fd, 3);

            // Write 4 blocks worth of data and check again
            byte[] buf = new byte[Disk.BLOCK_SIZE * 4];
            Arrays.fill(buf, (byte)'a');
            assertEquals(Disk.BLOCK_SIZE * 4, fs.write(fd, buf));
            assertEquals(8, fs.freeMap.find()); // used 4 more blocks
            testFileSize(fd, 3 + Disk.BLOCK_SIZE * 4);

            // Write 3 more bytes, introducing a hole
            byte[] bar = { 'b', 'a', 'r' };
            fs.seek(fd, Disk.BLOCK_SIZE * 2, Whence.SEEK_CUR); // hole
            assertEquals(Disk.BLOCK_SIZE * 6 + 3,
                         fs.seek(fd, 0, Whence.SEEK_CUR));
            assertEquals(3, fs.write(fd, bar));
            testFileSize(fd, Disk.BLOCK_SIZE * 6 + 6);

            // We introduced a hole, so even though the file is now
            // Disk.BLOCK_SIZE * 6 + 6 bytes long, using up what would
            // appear to be a total of 7 blocks (0 -- 6), in fact one
            // of those blocks is a hole. So, when we allocate a free
            // block, we should actually get the 7th block after the
            // first data block (and there are 3 metadata blocks, so
            // 7 + 3).
            assertEquals(7 + 3, fs.freeMap.find());
        }

        @Test
        public void testRead() throws IOException {
            int fd = fs.create();

            // First write some data in
            byte[] foo = { 'f', 'o', 'o' };
            assertEquals(3, fs.write(fd, foo));

            // Set seek pointer to 0 and make sure we can read what we wrote
            assertEquals(0, fs.seek(fd, 0, Whence.SEEK_SET));
            byte[] buf = new byte[foo.length];
            assertEquals(3, fs.read(fd, buf));
            assertTrue(Arrays.equals(foo, buf));
            assertEquals(3, fs.seek(fd, 0, Whence.SEEK_CUR));

            // Make sure that we read less if our buffer is smaller
            // than our file
            buf = new byte[1];
            fs.seek(fd, 0, Whence.SEEK_SET);
            assertEquals(1, fs.read(fd, buf));

            // Make sure that we read less if our file is smaller than
            // our buffer
            buf = new byte[10000];
            fs.seek(fd, 0, Whence.SEEK_SET);
            assertEquals(3, fs.read(fd, buf));
            
            // Seek beyond end of file and read; should return 0
            assertEquals(6, fs.seek(fd, 6, Whence.SEEK_SET));
            buf[0] = 'a'; buf[1] = 'b'; buf[2] = 'c';
            assertEquals(0, fs.read(fd, buf));
            assertEquals(6, fs.seek(fd, 0, Whence.SEEK_CUR));
            assertEquals(buf[0], 'a');
            assertEquals(buf[1], 'b');
            assertEquals(buf[2], 'c');

            // Introduce a hole
            byte[] bar = { 'b', 'a', 'r' };
            fs.seek(fd, Disk.BLOCK_SIZE * 2, Whence.SEEK_SET);
            assertEquals(3, fs.write(fd, bar));
            testFileSize(fd, Disk.BLOCK_SIZE * 2 + bar.length);

            // Read the file and check its contents
            buf = new byte[Disk.BLOCK_SIZE * 2 + bar.length];
            fs.seek(fd, 0, Whence.SEEK_SET);
            assertEquals(buf.length, fs.read(fd, buf));
            assertEquals('f', buf[0]);
            assertEquals('o', buf[1]);
            assertEquals('o', buf[2]);
            int i;
            for(i = foo.length; i < buf.length - bar.length; ++i)
                assertEquals('\0', buf[i]);
            assertEquals('b', buf[i + 0]);
            assertEquals('a', buf[i + 1]);
            assertEquals('r', buf[i + 2]);
        }

        @Test
        public void testOverwrite() throws IOException {
            int fd = fs.create();

            // Write to a block, then write to a different place in
            // that block, and make sure that the first write was not
            // overwritten with zeros (makes sure that non-fresh
            // blocks are read-before-updated).
            byte[] foo = { 'f', 'o', 'o' };
            byte[] bar = { 'b', 'a', 'r' };
            assertEquals(3, fs.write(fd, foo));
            assertEquals(6, fs.seek(fd, 3, Whence.SEEK_CUR));
            assertEquals(3, fs.write(fd, bar));
            testFileSize(fd, 9);
            byte[] buf = new byte[9];
            assertEquals(0, fs.seek(fd, 0, Whence.SEEK_SET));
            assertEquals(9, fs.read(fd, buf));
            assertEquals('f',  buf[0]);
            assertEquals('o',  buf[1]);
            assertEquals('o',  buf[2]);
            assertEquals('\0', buf[3]);
            assertEquals('\0', buf[4]);
            assertEquals('\0', buf[5]);
            assertEquals('b',  buf[6]);
            assertEquals('a',  buf[7]);
            assertEquals('r',  buf[8]);
        }
    }
}
