package KledingBib.demo.service;

import KledingBib.demo.dto.AccountDto;
import KledingBib.demo.exceptions.RecordNotFoundException;
import KledingBib.demo.models.Account;
import KledingBib.demo.models.Email;
import KledingBib.demo.models.User;
import KledingBib.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service


public class AccountService {


    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UploadRepository uploadRepository;
    private final EmailService emailService;
    private final SubscriptionService subscriptionService;
    private Account savedaccount;
    private UserService userDto;
    private Long savedUser;
    private java.lang.String String;


    public AccountService(AccountRepository accountRepository, UserRepository userRepository, ItemRepository itemRepository,
                          OrderRepository orderRepository, SubscriptionRepository subscriptionRepository,
                          UploadRepository uploadRepository, EmailService emailService, SubscriptionService subscriptionService) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionService = subscriptionService;

        this.uploadRepository = uploadRepository;
        this.emailService = emailService;

    }


    // GetMapping, method for getting all Accounts
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account : accounts) {
            AccountDto accountDto = transferAccountToAccountDto(account);
            accountDtos.add(accountDto);
        }


        return accountDtos;


    }
////  return   accountDtos;    55  bij exempel  (tvList)
    //    return transferAccountListToAccountDtoList(accounts);


    // GetMapping by id, method for getting a Account by id
    public AccountDto getAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account username = optionalAccount.get();
            return transferAccountToAccountDto(username);
        } else {
            throw new RecordNotFoundException("No account found with id: " + id + ".");
        }
    }

    // , method for adding a Account
    public Long createAccount(AccountDto accountDto) {


        Account newAccount;
        newAccount = transferAccountDtoToAccount(accountDto);
        Account savedAccount = accountRepository.save(newAccount);


        assignAccountToUser(String, savedUser);   ////nieuw

        Email email = new Email("ormenojavier452@gmail.com", "Er is een email verzonden");
        this.emailService.sendMail(email);

        return savedAccount.getId();
    }


    ////////////////////////////////////////////////////

    // PutMapping, method for changing a (whole)
    public AccountDto putAccount(Long id, AccountDto accountDto) {
        if (accountRepository.findById(id).isPresent()) {
            Account accountToChange = accountRepository.findById(id).get();
            Account account1 = transferAccountDtoToAccount(accountDto);
            account1.setId(accountToChange.getId());

            accountRepository.save(account1);
            return transferAccountToAccountDto(account1);
        } else {
            throw new RecordNotFoundException("No Account found with id: " + id + ".");
        }
    }


    //////////////////////////////////////////////////////


    // Patchmapping, method for changing parts

    public AccountDto patchAccount(Long id, AccountDto accountDto) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (accountRepository.existsById(id)) {
            Account accountToUpdate = optionalAccount.get();

            Account account1 = transferAccountDtoToAccount(accountDto);
            account1.setId(accountToUpdate.getId());

            if (accountDto.getId() != null) {
                accountToUpdate.setId(accountDto.getId());
            }
            if (accountDto.getUserInfo() != null) {
                accountToUpdate.setUserInfo(accountDto.getUserInfo());
            }

            if (accountDto.getSubscriptionInfo() != null) {
                accountToUpdate.setSubscriptionInfo(accountDto.getSubscriptionInfo());
            }
            if (accountDto.getEmail() != null) {
                accountToUpdate.setEmail(accountDto.getEmail());
            }
            if (accountDto.getComment() != null) {
                accountToUpdate.setComment(accountDto.getComment());
            }

            Account savedAccount = accountRepository.save(accountToUpdate);
            return transferAccountToAccountDto(savedAccount);
        } else {
            throw new RecordNotFoundException("No Account found with id " + id);

        }
    }


    ///////////////////////////////////////////////////////////
    // DeleteMapping, method for deleting
    public String deleteById(Long id) {
        if (accountRepository.existsById(id)) {
            Optional<Account> deletedAccount = accountRepository.findById(id);
            Account account1 = deletedAccount.get();

            // deleting upload, subscriptions  first

            //    for (Upload upload : account1.getUploads()) {
            //   Upload upload = account1.getUpload();
            //   uploadRepository.delete(upload);
            //     }
//            Subscription subscription = account1.getSubscription();
//
//            for (Subscription : account1.getSubscriptions()) {
//            Subscription subscription = account1.getSubscription();
//                subscriptionRepository.delete(subscription);
//           }

            accountRepository.delete(account1);
            return "Account with id: " + id + " deleted.";
        } else {
            throw new RecordNotFoundException("No Account found with id: " + id + ".");
        }
    }

    //////////////////////

    // Assign photo to a account
//     public void assignPhotoToAccount(String fileName, Long id) {
//        Optional<Account> optionalAccount = accountRepository.findById(id);
//         Optional<Upload> fileUploadResponse = uploadRepository.findByFileName(fileName);
//         if (optionalAccount.isPresent() && fileUploadResponse.isPresent()) {
//          Upload photo = fileUploadResponse.get();
//              Account account = optionalAccount.get();
//           account.setUpload(photo);
//             accountRepository.save(account);
//       }
//    }
    public void assignAccountToUser(String id, Long accountId) {     ////nieuw username String id,, Long accountId
        Optional<User> optionalUser = userRepository.findById(id);                        ///// nieuw username
        Optional<Account> optionalAccount = accountRepository.findById(accountId);  //  nieuw

        if (optionalUser.isPresent() && optionalAccount.isPresent()) {
            User user = optionalUser.get();
            Account account = optionalAccount.get();
            account.setUser(user);
            accountRepository.save(account);
        } else {
            throw new RecordNotFoundException();

        }
}

    //  methods
    //
    //  method from Account to Dto
    private AccountDto transferAccountToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUserInfo(account.getUserInfo());
        accountDto.setSubscriptionInfo(account.getSubscriptionInfo());
        accountDto.setEmail(account.getEmail());
        accountDto.setComment(account.getComment());
        accountDto.setUser(account.getUser());
//
        if (account.getUserInfo() != null) {
            accountDto.setUserInfo(account.getUserInfo());
        }
        if (account.getUpload() != null) {
            accountDto.setUpload(account.getUpload());
        }
        if (account.getSubscription() != null) {
            accountDto.setSubscription(account.getSubscription());
        }
//        if (account.getSubscription() != null) {
//            accountDto.setSubscriptionDto(SubscriptionService.transferAccountToAccountDto(account.getSubscription()));
//        }
        return accountDto;
    }
/////////////////////////


    /////////////////////////////////////////////

    //method from Dto to Acount
    public Account transferAccountDtoToAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setUserInfo(accountDto.getUserInfo());
        account.setSubscriptionInfo(accountDto.getSubscriptionInfo());
        account.setEmail(accountDto.getEmail());
        account.setComment(accountDto.getComment());
        account.setSubscription(accountDto.getSubscription());
        account.setUpload(accountDto.getUpload());

        return account;
    }
//



}



/////////////////////////
///////////Dit is nieuw
//    public List<AccountDto> transferAccountListToAccountDtoList(List<Account> accounts){
//        List<AccountDto> accountDtoList = new ArrayList<>();
//
//        for(Account account : accounts) {
//            AccountDto accountDto = transferAccountToAccountDto(account);
//            if(account.getSubscription() != null){
//                accountDto.setSubscriptionDto(subscriptionService.transferAccountToAccountDto(account.getSubscription()));
//            }
////            if(account.getRemoteController() != null){
////                accountDto.setRemoteControllerDto(remoteControllerService.transferToDto(account.getRemoteController()));
////            }
//            accountDtoList.add(accountDto);
//        }
//        return accountDtoList;
//    }

    ////////////////////////////////////////////////  dit stond ff uit
//    public List<Account> transferAccountDtoListToAccountList (List<AccountDto> usersdtos) {
//        List<Account> users = new ArrayList<>();
//        for (AccountDto usersdto : usersdtos) {
//            users.add(transferAccountDtoToAccount(usersdto));
//        }
//        return users;
//    }
//
//    public void getAccount(String userInfo, String subscriptionInfo) {
//    }
//



    // methods to add user, upload , account and subscription to these lists and connect to Account

//    public void addUploadToAccount(AccountDto accountDto, Account account) {
//        for (Upload upload : accountDto.getUpload()) {
//            if (!upload.getUpload().isEmpty()) {
//                upload.setAccount(account);
//                uploadRepository.save(upload);
//            }
//}
//    }

//    public void addOrderToAccount(AccountDto accountDto, Account account) {
//        for (Order order : accountDto.getOrders()) {
//            if (!order.getOrder().isEmpty()) {
//                order.setAccount(account);
//                orderRepository.save(order);
//            }
//        }
//    }
///////////////////////////////////////////////////////////////////// nieuw ///////////////
//    public void addSubscriptionToAccount(AccountDto accountDto, Account account) {
//       for (Subscription subscription : accountDto.getSubscriptions()) {
//           if (!subscription.getSubscription().isEmpty()) {
//               subscription.setAccount(account);
//               subscriptionRepository.save(subscription);
//           }
//       }}


///////////////////////////////////////////////////////////////////////
//    public void addUserToAccount(AccountDto accountDto, Account account) {
//        for (User user : accountDto.getUser()) {
//            if (!user.getUser().isEmpty()) {
//                user.setAccount(account);
//                userRepository.save(user);
//            }
//        }
////////////////////////////////////////////////

 //   public void assignAccountToUser(String id, String accountId) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        Optional<Account> optionalAccount = accountRepository.findById(accountId);
//        if (optionalUser.isPresent() && optionalAccount.isPresent()) {
//            User user = optionalUser.get();
//            Account account = optionalAccount.get();
//            account.setUser(user);
//            accountRepository.save(account);
//        } else {
//            throw new RecordNotFoundException();
//
//        }}





///////////////////   from subscription blablabla ///////////
//    public Subscription(Long id, String typeSubscription, LocalDate expirationDate) {
//        this.id = id;
//        this.typeSubscription = typeSubscription;
//        this.expirationDate = expirationDate;
//    }

//    public Subscription(long l, String subscription2, Object o) {
//    }

//    public Subscription(SubscriptionStatus expire) {
//    }
