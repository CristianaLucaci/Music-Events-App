package sample.services;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class InviteServiceTest {

    @BeforeClass
    public static void setupClass(){
        FileSystemService.APPLICATION_FOLDER=".test-musicevents";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
    }

    @Test
    public void testCopyDefaultFileNotExists() throws Exception{
        InviteService.loadInvitesFromFile();
        assertTrue(Files.exists(InviteService.INVITES_PATH));
    }

    @Test
    public void testLoadInvitesFromFile() throws Exception{
        InviteService.loadInvitesFromFile();
        assertNotNull(InviteService.invites);
        assertEquals(0,InviteService.invites.size());
    }

}