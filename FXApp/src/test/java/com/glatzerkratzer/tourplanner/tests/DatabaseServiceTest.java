package com.glatzerkratzer.tourplanner.tests;

import com.glatzerkratzer.tourplanner.database.DatabaseService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseServiceTest {

    @InjectMocks private DatabaseService databaseService;
    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;

    @BeforeEach
    public void initDatabaseService() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDatabaseServiceIsNotNull() {
        // arrange & act ???
        databaseService = DatabaseService.getDatabaseService();

        // assert
        assertNotNull(databaseService);
    }

    @Test
    void testGetConnectionIsNotNoll() {
        // arrange & act
        mockConnection = DatabaseService.getDatabaseService().getConnection();
        assertNotNull(mockConnection);
    }
}