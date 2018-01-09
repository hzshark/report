package cn.xp.hashpower;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void  trancation() throws Exception {

		String toAddress="4246a241d0a2cf20b548c7628f8230b170482dcb";
		String address="";
		String walletfile="C:\\Users\\liuxp\\AppData\\Roaming\\Ethereum\\keystore\\UTC--2017-12-22T03-06-16.648864200Z--f04f3434c15819c6513474ba099d9fdc3e5339c8";
		File file =new File(walletfile);
		if (!file.exists()) {
			System.out.println("file not exists ");
			System.exit(1);
		}
			Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
		Credentials credentials = WalletUtils.loadCredentials("123456", walletfile);
		try {
			TransactionReceipt transactionReceipt = Transfer.sendFunds(
					web3, credentials, /*"0x<address>|<ensName>"*/toAddress,
					BigDecimal.valueOf(1.0), Convert.Unit.ETHER)
					.send();
			BigInteger gasfee=transactionReceipt.getCumulativeGasUsed();
		}catch (TransactionException tx)
		{
			System.out.println(tx.getMessage());
			System.exit(1);
		}


		//Or if you wish to create your own custom transaction:

		//Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
		//Credentials credentials = WalletUtils.loadCredentials("password", "/path/to/walletfile");

// get the next available nonce
		EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
				address, DefaultBlockParameterName.LATEST).sendAsync().get();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

// create our transaction
		//RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(nonce, <gas price>, <gas limit>, <toAddress>, <value>);

// sign & send our transaction
		//byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		//String hexValue = Hex.toHexString(signedMessage);
		//EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
	}
}
