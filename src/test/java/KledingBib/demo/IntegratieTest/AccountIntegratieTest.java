package KledingBib.demo.IntegratieTest;

import KledingBib.demo.controller.AccountController;
import KledingBib.demo.dto.AccountDto;
import KledingBib.demo.repository.AccountRepository;
import KledingBib.demo.repository.SubscriptionRepository;
import KledingBib.demo.service.AccountService;
import KledingBib.demo.service.UploadService;
import KledingBib.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest  //(AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@ContextConfiguration(classes = {AccountService.class, UploadService.class, UserService.class})
class AccountIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private UploadService uploadService;

    @MockBean
    private UserService userService;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void testGetAllAccounts() throws Exception {
        // Arrange
        AccountDto accountDto = new AccountDto();
        accountDto.setId(1L);
        accountDto.setUserInfo("user15");

        when(accountService.getAllAccounts()).thenReturn(Collections.singletonList(accountDto));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(15))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameInfo").value("user15"));
    }

    @Test
    public void testCreateAccount() throws Exception {
        // Arrange
        AccountDto accountDto = new AccountDto();
        accountDto.setUserInfo("user15");

        when(accountService.createAccount(accountDto)).thenReturn(1L);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userInfo\": \"user15\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
              //  .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost:8083/accounts/10"))
                .andExpect(MockMvcResultMatchers.content().string("15"));
    }

    // Voeg andere testmethoden toe om andere functionaliteiten van de AccountController te testen

}
