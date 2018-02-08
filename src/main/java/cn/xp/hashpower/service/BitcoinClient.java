package cn.xp.hashpower.service;

import cn.xp.hashpower.util.Utils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoinRpcException;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;
import wf.bitcoin.krotjson.JSON;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;



/**
 * Created by fpeters on 11-01-17.
 */
@Log4j
@Component
public class BitcoinClient {

    BitcoinJSONRPCClient bitcoin;

    @Value("${btcoin.rpc.url}")
    String url;

    @Value("${btcoin.sys.wallet}")
    String systemBtcWalletAddress;
   // private static ResourceBundle resb = ResourceBundle.getBundle("app");
    public BitcoinClient()  {
        String defurl="http://RPCuser:RPCpwd@127.0.0.1:8335";
        if (Utils.isBlank(url))
            url=defurl;
        try {
            bitcoin  = new BitcoinJSONRPCClient(new URL(url));
        }catch (Exception e)
        {

        }
    }




    public String  CreateWallet(String userinfo) throws Exception {

        String account=bitcoin.getAccountAddress(userinfo);
        log.info("create account address :" +account +" with "+userinfo );
        return account;
    }

    @Cacheable(value = "localCache")
    public double getbalance(String address) throws Exception {

        double balance = bitcoin.getBalance(address);
        log.info(address + " balance: "+ balance);
        return balance;
    }


    public String sendBtcoin(String from,String to,double amount) throws Exception {
        String ret= bitcoin.sendFrom(from,to,amount);
        log.info(" sendBtcoin from from -->"+to);
        return ret;
    }

    public String sendBtcoinToSysWallet(String from,float amount) throws Exception
    {
        return  sendBtcoin(from,systemBtcWalletAddress,amount);
    }


   /* public void signRawTransactionTest() throws Exception {
        client = new MyClientTest(false, "signrawtransaction", null,
                "{\n" +
                        "  \"hex\": \"0100000001b8b2244faca910c1ffff24ecd2b559b4699338398bf77e4cb1fdeb19ad419ea0010000006b483045022100b68b7fe9cfabb32949af6747b6769dffcf2aa4170e4df2f0e9d0a4571989e94e02204cf506c210cdb6b6b4413bf251a0b57ebcf1b1b2d303ba6183239b557ef0a310012102ab46e1d7b997d8094e97bc06a21a054c2ef485fac512e2dc91eb9831af55af4effffffff012e2600000000000017a9140b2d7ed4e5076383ba8e98b9b3bce426b7a2ea1e8700000000\",\n" +
                        "  \"complete\": true\n" +
                        "}\n");

        LinkedList<BitcoindRpcClient.ExtendedTxInput> inputList = new LinkedList<BitcoindRpcClient.ExtendedTxInput>();
        LinkedList<String> privateKeys = new LinkedList<String>();
        privateKeys.add("cSjzx3VAM1r9iLXLvL6N61oS3zKns9Z9DcocrbkEzesPTDHWm5r4");
        String hex = client.signRawTransaction("0100000001B8B2244FACA910C1FFFF24ECD2B559B4699338398BF77E4CB1FDEB19AD419EA0010000001976A9144CB4C3B90994FEF58FABB6D8368302E917C6EFB188ACFFFFFFFF012E2600000000000017A9140B2D7ED4E5076383BA8E98B9B3BCE426B7A2EA1E8700000000",
                inputList, privateKeys, "ALL");
        assertEquals("0100000001b8b2244faca910c1ffff24ecd2b559b4699338398bf77e4cb1fdeb19ad419ea0010000006b483045022100b68b7fe9cfabb32949af6747b6769dffcf2aa4170e4df2f0e9d0a4571989e94e02204cf506c210cdb6b6b4413bf251a0b57ebcf1b1b2d303ba6183239b557ef0a310012102ab46e1d7b997d8094e97bc06a21a054c2ef485fac512e2dc91eb9831af55af4effffffff012e2600000000000017a9140b2d7ed4e5076383ba8e98b9b3bce426b7a2ea1e8700000000",
                hex);
    }

    public void signRawTransactionTestException() throws Exception {
        client = new MyClientTest(false, "signrawtransaction", null,
                "{\n" +
                        "  \"hex\": \"0100000001b8b2244faca910c1ffff24ecd2b559b4699338398bf77e4cb1fdeb19ad419ea00100000000ffffffff012e2600000000000017a9140b2d7ed4e5076383ba8e98b9b3bce426b7a2ea1e8700000000\",\n" +
                        "  \"complete\": false,\n" +
                        "  \"errors\": [\n" +
                        "    {\n" +
                        "      \"txid\": \"a09e41ad19ebfdb14c7ef78b39389369b459b5d2ec24ffffc110a9ac4f24b2b8\",\n" +
                        "      \"vout\": 1,\n" +
                        "      \"scriptSig\": \"\",\n" +
                        "      \"sequence\": 4294967295,\n" +
                        "      \"error\": \"Operation not valid with the current stack size\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}");
        LinkedList<BitcoindRpcClient.ExtendedTxInput> inputList = new LinkedList<BitcoindRpcClient.ExtendedTxInput>();
        LinkedList<String> privateKeys = new LinkedList<String>();
        try {
            String hex = client.signRawTransaction("0100000001B8B2244FACA910C1FFFF24ECD2B559B4699338398BF77E4CB1FDEB19AD419EA0010000001976A9144CB4C3B90994FEF58FABB6D8368302E917C6EFB188ACFFFFFFFF012E2600000000000017A9140B2D7ED4E5076383BA8E98B9B3BCE426B7A2EA1E8700000000",
                    inputList, privateKeys, "ALL");
        }
        catch(Exception e) {
            assertThat(e.getMessage(), is("Incomplete"));
        }
    }

    public void signRawTransactionTest2() throws Exception {
        client = new MyClientTest(false, "signrawtransaction", null,
                "{\n" +
                        "  \"hex\": \"0100000001b8b2244faca910c1ffff24ecd2b559b4699338398bf77e4cb1fdeb19ad419ea0010000006b483045022100b68b7fe9cfabb32949af6747b6769dffcf2aa4170e4df2f0e9d0a4571989e94e02204cf506c210cdb6b6b4413bf251a0b57ebcf1b1b2d303ba6183239b557ef0a310012102ab46e1d7b997d8094e97bc06a21a054c2ef485fac512e2dc91eb9831af55af4effffffff012e2600000000000017a9140b2d7ed4e5076383ba8e98b9b3bce426b7a2ea1e8700000000\",\n" +
                        "  \"complete\": true\n" +
                        "}\n");
        String hex = client.signRawTransaction("0100000001B8B2244FACA910C1FFFF24ECD2B559B4699338398BF77E4CB1FDEB19AD419EA0010000001976A9144CB4C3B90994FEF58FABB6D8368302E917C6EFB188ACFFFFFFFF012E2600000000000017A9140B2D7ED4E5076383BA8E98B9B3BCE426B7A2EA1E8700000000");
        assertEquals("0100000001b8b2244faca910c1ffff24ecd2b559b4699338398bf77e4cb1fdeb19ad419ea0010000006b483045022100b68b7fe9cfabb32949af6747b6769dffcf2aa4170e4df2f0e9d0a4571989e94e02204cf506c210cdb6b6b4413bf251a0b57ebcf1b1b2d303ba6183239b557ef0a310012102ab46e1d7b997d8094e97bc06a21a054c2ef485fac512e2dc91eb9831af55af4effffffff012e2600000000000017a9140b2d7ed4e5076383ba8e98b9b3bce426b7a2ea1e8700000000",
                hex);
    }*/
}