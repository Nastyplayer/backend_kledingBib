//package KledingBib.demo.controller;
//
//import KledingBib.demo.dto.SubscriptionDto;
//import KledingBib.demo.models.Subscription;
//import KledingBib.demo.repository.SubscriptionRepository;
//import KledingBib.demo.service.SubscriptionService;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
//@ActiveProfiles("test")
//
//
//
//class SubscriptionControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    SubscriptionService subscriptionService;
//
//
//    @Autowired
//    SubscriptionRepository subscriptionRepository;
//
//    Subscription subscription1;
//    Subscription subscription2;
//    SubscriptionDto subscriptionDto1;
//    SubscriptionDto subscriptionDto2;
//    SubscriptionDto subscriptionDto3;
//
//
//    @BeforeEach
//    void setUp() {
//        subscription1 = new Subscription(1L, "Subscription1", null);
//        subscription2 = new Subscription(2L, "Subscription2", null);
//
//        subscriptionRepository.save(subscription1);
//        subscriptionRepository.save(subscription2);
//
//        //with Subscription.getId
//        subscriptionDto1 = new SubscriptionDto(subscription1.getId(), "Subscription1", null);
//        subscriptionDto2 = new SubscriptionDto(subscription2.getId(), "Subscription2", null);
//        //with hardcoded id
//        subscriptionDto3 = new SubscriptionDto(3L, "Subscription3", null);
//    }
//
//
//    @Test
//    void getAllSubscription() throws Exception {
//        mockMvc.perform(get("/subscription"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(subscription1.getId().toString()))
//                .andExpect(jsonPath("$[0].subscription").value("Subscription1"))
//        ;
//    }
//
//
//    @Test
//    void getSubscription() throws Exception {
//        mockMvc.perform(get("/subscription/" + subscription1.getId().toString()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(subscription1.getId().toString()))
//                .andExpect(jsonPath("subscription").value("Subscription1"))
//        ;
//    }
//
//    @Test
//    void createSubscription() throws Exception {
//        mockMvc.perform(post("/subscription")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(subscriptionDto1)))
//                .andExpect(status().isCreated())
//        ;
//    }
//
//
//    @Test
//    void putSubscription() throws Exception {
//        mockMvc.perform(put("/subscription/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(subscriptionDto2)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(1L))
//                .andExpect(jsonPath("subscription").value("Subscription2"))
//        ;
//    }
//
//
//    @Test
//    void patchSubscription() throws Exception {
//        mockMvc.perform(put("/subscription/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(subscriptionDto2)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(1L))
//                .andExpect(jsonPath("subscription").value("Subscription2"))
//        ;
//    }
//
//
//    @Test
//    void deleteById() throws Exception {
//        mockMvc.perform(delete("/subscription/1"))
//                .andExpect(status().isNoContent());
//    }
//
//
//    public static String asJsonString(final SubscriptionDto obj) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
//            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//            return mapper.writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//
//
//
