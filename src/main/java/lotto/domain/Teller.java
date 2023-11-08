package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class Teller {
  private InputValidation validate = new InputValidation();
  private final Integer INITIALSTATE = -1;

  public Integer getMoneyAmount() {
    System.out.println(Speaker.PROMPTBUYAMOUNT.speak());
    return getValidatedMoneyAmount();
  }

  public Integer getValidatedMoneyAmount() {
    Integer validInput = INITIALSTATE;
    do {
      String input = Console.readLine();
      try {
        validInput = validate.moneyInput(input);
        break;
      }
      catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
      }
    }
    while (true);
    return validInput;
  }

  public void showMoneyAmount(Integer lottoAmount) {
    System.out.println();
    System.out.print(lottoAmount);
    System.out.println(Speaker.BOUGHTAMOUNT.speak());
  }

  public void showLottoNumbers(ArrayList<Lotto> reciept) {
    for (Lotto lotto : reciept) {
      System.out.println(lotto.toString());
    }
  }

  public List<Integer> getLottoNumbers() {
    System.out.println();
    System.out.println(Speaker.PROMPTLOTTO.speak());
    return getValidatedLottoNumbers();
  }

  public List<Integer> getValidatedLottoNumbers() {
    ArrayList<Integer> lottoNumbers;
    while (true) {
      lottoNumbers = new ArrayList<>();
      try {
        String input = Console.readLine();
        validate.emptyInput(input);
        String[] numbers = validate.lottoInput(input);
        fillLottoNumbers(lottoNumbers, numbers);
        break;
      }
      catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
      }
    }
    return lottoNumbers;
  }

  public void fillLottoNumbers(List<Integer> lottoNumbers, String[] numbers) {
    for (String number : numbers) {
      Integer convertedNumber = Integer.parseInt(number);
      validate.lottoDuplicate(lottoNumbers, convertedNumber);
      validate.lottoRange(convertedNumber);
      lottoNumbers.add(convertedNumber);
    }
  }

  public Integer getBonusNumber(List<Integer> lottoNumbers) {
    System.out.println();
    System.out.println(Speaker.PROMPTBONUS.speak());
    Integer bonusNumber;
    while (true) {
      try {
        String input = Console.readLine();
        bonusNumber = validate.bonusNumber(input);
        validate.lottoDuplicate(lottoNumbers, bonusNumber);
        break;
      }
      catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
      }
    }
    return bonusNumber;
  }
}
