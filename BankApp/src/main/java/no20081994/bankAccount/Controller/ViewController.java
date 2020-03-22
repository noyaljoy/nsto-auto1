package no20081994.bankAccount.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import no20081994.bankAccount.Model.Accounts;
import no20081994.bankAccount.Model.FundTransfer;
import no20081994.bankAccount.Model.Transactions;
import no20081994.bankAccount.Service.BankAccountService;
import no20081994.bankAccount.Util.BankTransactionException;

@Controller
public class ViewController {
	
	@Autowired
	private BankAccountService accountService;
	
	@RequestMapping(value = {"/"})
	public String showIndex()
	{
		return "index";
	}
	
	@RequestMapping(value = "/accountsList")
	public String getAccountList(Model model)
	{
		List<Accounts> list = accountService.getAccountList();
		model.addAttribute("accounts", list);
		return "allAccounts";
	}
	
	@RequestMapping(value = "/transactions/{accountNumber}")
	public String getAllTransactions(@PathVariable("accountNumber") Long accountNumber,Model model)
	{
		List<Transactions> transactions = accountService.getAllTransactions(accountNumber);
		model.addAttribute("transactions", transactions);
		return "transactions";
	}
	
	@RequestMapping(value = "/transferPage")
	public String fundTransferPage(Map<String, Object> model)
	{
		Map<Long, String> accountList = accountService.getAccountDropDownList();
		model.put("accountList", accountList);
		model.put("fundTransfer", new FundTransfer());
		return "transfer";
	}
	
	@RequestMapping(value = "/transfer")
	public String fundTransfer(@ModelAttribute FundTransfer fundTransfer,Model model) throws BankTransactionException
	{
		accountService.transferFunds(fundTransfer);
		List<Accounts> list = accountService.getAccountList();
		model.addAttribute("accounts", list);
		return "allAccounts";
	}
	
}