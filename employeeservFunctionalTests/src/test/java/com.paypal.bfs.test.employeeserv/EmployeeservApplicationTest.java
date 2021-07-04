//package com.paypal.bfs.test.employeeserv;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * @author jmahajan
// * Mock Application controller. Health Check support can be added and removed here.
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@EnableAutoConfiguration(exclude = BatchAutoConfiguration.class)
//public class EmployeeservApplicationTest {
//
//    @LocalServerPort
//    int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void testHealthCheck() {
//        String body =
//                this.restTemplate.getForObject(
//                        "http://localhost:" + this.port + "/health/full", String.class);
//        assertThat(body).contains("\"status\":404");
//    }
//}