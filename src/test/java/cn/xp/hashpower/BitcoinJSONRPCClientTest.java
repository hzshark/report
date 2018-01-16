package cn.xp.hashpower;

import org.junit.Before;
import org.junit.Test;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoinRpcException;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

import wf.bitcoin.krotjson.JSON;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by fpeters on 11-01-17.
 */

public class BitcoinJSONRPCClientTest {

    class MyClientTest extends BitcoinJSONRPCClient {

        String expectedMethod;
        Object[] expectedObject;
        String result;

        MyClientTest(boolean testNet, String expectedMethod, Object[] expectedObject, String result) {
            super(testNet);
            this.expectedMethod = expectedMethod;
            this.expectedObject = expectedObject;
            this.result = result;
        }

        @Override
        public Object query(String method, Object... o) throws BitcoinRpcException {
            if(method!=expectedMethod) {
                throw new BitcoinRpcException("wrong method");
            }
            if(o.equals(expectedObject)){
                throw new BitcoinRpcException("wrong object");
            }
            return JSON.parse(result);
        }
    }

    MyClientTest client;

    @Test
    public void CreateWalletTest() throws Exception {
        final String rpcuser ="RPCuser";
        final String rpcpassword ="RPCpwd";
        Short a =128;
        int  c = 3;
        byte b = (byte)c;


        /*Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(rpcuser, rpcpassword.toCharArray());
            }
        });*/

        //BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@182.92.97.3:8333"));
       // Bitcoin bitcoin = new BitcoinJSONRPCClient();
        //BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@192.168.2.66:8333"));
        BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@127.0.0.1:8335"));

        //BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://127.0.0.1:8333"));


        // BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@127.0.0.1:8332"));
        //String address = bitcoin.getAccountAddress("9999999999999999999");
        BitcoindRpcClient.Info zz= bitcoin.getInfo();
        System.out.println(zz);
        String account=bitcoin.getAccountAddress("13355786900@qq.com");
        System.out.println(account);
        return;
    }

    public void getbalance(String address) throws Exception {
        final String rpcuser ="RPCuser";
        final String rpcpassword ="RPCpwd";


        //BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@182.92.97.3:8333"));
        // Bitcoin bitcoin = new BitcoinJSONRPCClient();
        //BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@192.168.2.66:8333"));
        BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@127.0.0.1:8335"));

        //BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://127.0.0.1:8333"));


        // BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@127.0.0.1:8332"));
        //String address = bitcoin.getAccountAddress("9999999999999999999");
        /*BitcoindRpcClient.Info zz= bitcoin.getInfo();
        System.out.println(zz);*/
        ArrayList<String> accounts=new ArrayList<>();
        accounts.add("13355786900");
        accounts.add("lxp.hz@qq.com");
        //String address;
        for (String account:accounts) {
             address = bitcoin.getAccountAddress(account);
             System.out.println(address);
        }
        /*double balance = bitcoin.getBalance(account);
        System.out.println(account + " balance: "+ balance);*/
        return;
    }


    @Test
    public void sendBtcoin(/*String from,String to,double amount,String common*/) throws Exception {
        final String rpcuser ="RPCuser";
        final String rpcpassword ="RPCpwd";


        //BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@182.92.97.3:8333"));
        // Bitcoin bitcoin = new BitcoinJSONRPCClient();
        //BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@192.168.2.66:8333"));
        BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@127.0.0.1:8335"));

        //BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://127.0.0.1:8333"));


        // BitcoinJSONRPCClient bitcoin  = new BitcoinJSONRPCClient(new URL("http://RPCuser:RPCpwd@127.0.0.1:8332"));
        //String address = bitcoin.getAccountAddress("9999999999999999999");
        /*BitcoindRpcClient.Info zz= bitcoin.getInfo();
        System.out.println(zz);*/
       /* ArrayList<String> accounts=new ArrayList<>();
        accounts.add("13355786900");
        accounts.add("lxp.hz@qq.com");
        String address;
        for (String account:accounts) {
            address = bitcoin.getAccountAddress(account);
            System.out.println(address);
        }*/
       String ret= bitcoin.sendFrom("n2GNHrZmethYoTpu77XztxotAthH2batf3","n3fFEpepxBbMCGUgLFgxuniemSGCXyxuwT",0.00001);
        System.out.println(" sendFrom : "+ ret);
        bitcoin.listAccounts();
        return;
    }

    @Test
    public void signRawTransactionTest() throws Exception {
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

    @Test
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

    @Test
    public void signRawTransactionTest2() throws Exception {
        client = new MyClientTest(false, "signrawtransaction", null,
                "{\n" +
                        "  \"hex\": \"0100000001b8b2244faca910c1ffff24ecd2b559b4699338398bf77e4cb1fdeb19ad419ea0010000006b483045022100b68b7fe9cfabb32949af6747b6769dffcf2aa4170e4df2f0e9d0a4571989e94e02204cf506c210cdb6b6b4413bf251a0b57ebcf1b1b2d303ba6183239b557ef0a310012102ab46e1d7b997d8094e97bc06a21a054c2ef485fac512e2dc91eb9831af55af4effffffff012e2600000000000017a9140b2d7ed4e5076383ba8e98b9b3bce426b7a2ea1e8700000000\",\n" +
                        "  \"complete\": true\n" +
                        "}\n");
        String hex = client.signRawTransaction("0100000001B8B2244FACA910C1FFFF24ECD2B559B4699338398BF77E4CB1FDEB19AD419EA0010000001976A9144CB4C3B90994FEF58FABB6D8368302E917C6EFB188ACFFFFFFFF012E2600000000000017A9140B2D7ED4E5076383BA8E98B9B3BCE426B7A2EA1E8700000000");
        assertEquals("0100000001b8b2244faca910c1ffff24ecd2b559b4699338398bf77e4cb1fdeb19ad419ea0010000006b483045022100b68b7fe9cfabb32949af6747b6769dffcf2aa4170e4df2f0e9d0a4571989e94e02204cf506c210cdb6b6b4413bf251a0b57ebcf1b1b2d303ba6183239b557ef0a310012102ab46e1d7b997d8094e97bc06a21a054c2ef485fac512e2dc91eb9831af55af4effffffff012e2600000000000017a9140b2d7ed4e5076383ba8e98b9b3bce426b7a2ea1e8700000000",
                hex);
    }
}