//package digital.paisley.phonebook.services;
//
//import digital.paisley.phonebook.clients.DummyClient;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.initMocks;
//
//class DummyServiceTest {
//
//    @Mock
//    private DummyClient mockDummyClient;
//    @Mock
//    private DummyRepository mockDummyRepository;
//
//    private DummyService dummyServiceUnderTest;
//
//    @BeforeEach
//    void setUp() {
//        initMocks(this);
//        dummyServiceUnderTest = new DummyService(mockDummyClient, mockDummyRepository);
//    }
//
//    @Test
//    void testGetDummyById() {
//        // Setup
//
//        // Configure DummyRepository.getOne(...).
//        final Dummy dummy = new Dummy();
//        dummy.setId(0L);
//        dummy.setDummy("dummy");
//        when(mockDummyRepository.getOne(0L)).thenReturn(dummy);
//
//        // Run the test
//        final String result = dummyServiceUnderTest.getDummyById(0L);
//
//        // Verify the results
//        assertEquals("dummy", result);
//    }
//
//    @Test
//    void testGetDummyByIdFromClient() {
//        // Setup
//        when(mockDummyClient.getById(0L)).thenReturn("result");
//
//        // Run the test
//        final String result = dummyServiceUnderTest.getDummyByIdFromClient(0L);
//
//        // Verify the results
//        assertEquals("result", result);
//    }
//}
