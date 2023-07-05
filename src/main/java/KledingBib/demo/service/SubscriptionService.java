package KledingBib.demo.service;

import KledingBib.demo.dto.SubscriptionDto;

import KledingBib.demo.exceptions.RecordNotFoundException;


import KledingBib.demo.models.Account;
import KledingBib.demo.models.Subscription;
import KledingBib.demo.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

        public List<SubscriptionDto> getAllSubscriptions () {
            List<Subscription> subscriptions = subscriptionRepository.findAll();
            ArrayList<SubscriptionDto> subscriptionDtos = new ArrayList<>();
            for (Subscription Subscription : subscriptions) {
                SubscriptionDto subscriptionDto = transferSubscriptionToSubscriptionDto(Subscription);
                subscriptionDtos.add(subscriptionDto);
            }
            return subscriptionDtos;
        }

  //  private SubscriptionDto transferSubscriptionToSubscriptionDto(Subscription subscription) {
   //   return null;
 //  }

    public SubscriptionDto getSubscription(Long id) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);
        if (optionalSubscription.isPresent()) {
            Subscription username = optionalSubscription.get();
            return transferSubscriptionToSubscriptionDto(username);
        } else {
            throw new RecordNotFoundException("No ingredient found with id: " + id + ".");
        }
    }

    public Long createSubscription(SubscriptionDto subscriptionDto) {
        Subscription newSubscription;
        newSubscription = transferSubscriptionDtoToSubscription(subscriptionDto);
        Subscription savedSubscription = subscriptionRepository.save(newSubscription);
        return savedSubscription.getId();
    }
//////
  //private Subscription transferSubscriptionDtoToSubscription(SubscriptionDto subscriptionDto) {
   //   return null;
  // }
//////
    public SubscriptionDto putSubscription(Long id, SubscriptionDto subscriptionDto) {
            if (subscriptionRepository.findById(id).isPresent()) {
                Subscription subscriptionToChange = subscriptionRepository.findById(id).get();
                Subscription subscription1 = transferSubscriptionDtoToSubscription(subscriptionDto);
                subscription1.setId(subscriptionToChange.getId());

                subscriptionRepository.save(subscription1);
                return transferSubscriptionToSubscriptionDto(subscription1);
            } else {
                throw new RecordNotFoundException("No Subscription found with id: " + id + ".");
            }
        }


    public SubscriptionDto patchSubscription(Long id, SubscriptionDto subscriptionDto) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);
        if (subscriptionRepository.existsById(id)) {
            Subscription subscriptionToUpdate = optionalSubscription.get();
///
            Subscription subscription1 = transferSubscriptionDtoToSubscription(subscriptionDto);
            subscription1.setId(subscriptionToUpdate.getId());
//////
            if (subscriptionDto.getType() != null) {
                subscriptionToUpdate.setType(subscriptionDto.getType());
            }
            if (subscriptionDto.getExpirationDate() != null) {
                subscriptionToUpdate.setExpirationDate(subscriptionDto.getExpirationDate());
            }

            Subscription savedSubscription = subscriptionRepository.save(subscriptionToUpdate);
            return transferSubscriptionToSubscriptionDto(savedSubscription);
        } else {
            throw new RecordNotFoundException("No Subscription with id " + id);
        }
    }

    public String deleteById(Long id) {
        if (subscriptionRepository.existsById(id)) {
            Optional<Subscription> deletedSubscription = subscriptionRepository.findById(id);
            Subscription subscription1 = deletedSubscription.get();
            subscriptionRepository.delete(subscription1);
            return "Subscription with id: " + id + " deleted.";
        } else {
            throw new RecordNotFoundException("No Subscription found with id: " + id + ".");
        }
    }


    //    helper methods.......................................................
    private SubscriptionDto transferSubscriptionToSubscriptionDto(Subscription subscription) {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setAccount(subscription.getAccount());
        subscriptionDto.setId(subscription.getId());
        subscriptionDto.setType(subscription.getType());
        subscriptionDto.setExpirationDate(subscription.getExpirationDate());

        if (subscription.getAccount() != null) {
            subscriptionDto.setAccount(subscription.getAccount());
        }
        return subscriptionDto;
    }

    private Subscription transferSubscriptionDtoToSubscription(SubscriptionDto subscriptionDto) {
        Subscription subscription = new Subscription();
        subscription.setAccount(subscriptionDto.getAccount());
        subscription.setId(subscriptionDto.getId());
        subscription.setType(subscriptionDto.getType());
        subscription.setExpirationDate(subscriptionDto.getExpirationDate());

        return subscription;
    }

    public List<Subscription> transferSubscriptionDtoListToSubscriptionList(List<SubscriptionDto> subscriptionsdtos) {
        List<Subscription> subscriptions = new ArrayList<>();
        for (SubscriptionDto subscriptionsdto : subscriptionsdtos) {


            subscriptions.add(transferSubscriptionDtoToSubscription(subscriptionsdto));
        }
        return subscriptions;
    }
}