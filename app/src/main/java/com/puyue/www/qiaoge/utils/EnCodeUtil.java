package com.puyue.www.qiaoge.utils;


import android.util.Log;


import org.apache.commons.codec1.binary.Base64;

import  javax.crypto.BadPaddingException;
import  javax.crypto.Cipher;
import  javax.crypto.IllegalBlockSizeException;
import  javax.crypto.NoSuchPaddingException;

import java.io.ByteArrayOutputStream;
import  java.security.InvalidKeyException;
import java.security.Key;
import  java.security.KeyFactory;
import  java.security.NoSuchAlgorithmException;
import  java.security.interfaces.RSAPublicKey;
import  java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import  java.security.spec.X509EncodedKeySpec;

/**
 * Created by ${王涛} on 2020/6/9
 */
public class EnCodeUtil {
    /**
     *  字符串公钥加密
     *
     *  @param  str
     *  @return
     */

    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "RSA/ECB/PKCS1Padding";

    /**
     * 加密算法RSA
     */
    private static final String KEY_ALGORITHM = "RSA";


    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;


    /**
     * 公钥加密
     *
     * @param data
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String publicKeyStr) throws Exception {
        byte[] dataByte = data.getBytes("UTF-8");
        byte[] keyBytes = Base64.decodeBase64(publicKeyStr);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher =  Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = dataByte.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(dataByte, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataByte, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.encodeBase64String(encryptedData);

    }

//    public  static  String  encrypt(String  str)  {
//        String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTykrDv1TEKVjDeE29kVLo5M7mctlE65WlHSMN8RVL1iA9jXsF9SMNH1AErs2lqxpv18fd3TOAw0pBaG+cXOxApKdvRDKgxyuHnONOBzxr6EyWOQlRZt94auL1ESVbLdvYa7+cISkVe+MphfQh7uI/64tGQ34aRNmvFKv9PEeBTQIDAQAB";
//        try  {
//            RSAPublicKey  rsaPublicKey  =  loadPublicKeyByStr(publicKeyStr);
//            Log.d("shuju........",rsaPublicKey+"");
//            byte[]  encryByte  =  encrypt(rsaPublicKey, str.getBytes());
//            return  Base64.encodeBase64String(encryByte);
//        }  catch  (Exception  e)  {
//            return  null;
//        }
//    }


    /**
     *  从字符串中加载公钥
     *
     *  @param  publicKeyStr  公钥数据字符串
     *  @throws  Exception  加载公钥时产生的异常
     */
//    public  static  RSAPublicKey  loadPublicKeyByStr(String  publicKeyStr)
//            throws  Exception  {
//        try  {
//            byte[]  buffer  =  Bases64.decode(publicKeyStr);
//            KeyFactory  keyFactory  =  KeyFactory.getInstance("RSA");
//            X509EncodedKeySpec  keySpec  =  new  X509EncodedKeySpec(buffer);
//            return  (RSAPublicKey)  keyFactory.generatePublic(keySpec);
//        }  catch  (NoSuchAlgorithmException  e)  {
//            throw  new  Exception("无此算法");
//        }  catch  (InvalidKeySpecException  e)  {
//            throw  new  Exception("公钥非法");
//        }  catch  (NullPointerException  e)  {
//            throw  new  Exception("公钥数据为空");
//        }
//    }
//
//
//    /**
//     *  公钥加密过程
//     *
//     *  @param  publicKey          公钥
//     *  @param  plainTextData  明文数据
//     *  @return
//     *  @throws  Exception  加密过程中的异常信息
//     */
//    public  static  byte[]  encrypt(RSAPublicKey  publicKey,  byte[]  plainTextData)
//            throws  Exception  {
//        if  (publicKey  ==  null)  {
//            throw  new  Exception("加密公钥为空,  请设置");
//        }
//        Cipher  cipher  =  null;
//        try  {
//            //  使用默认RSA
//            cipher  =  Cipher.getInstance("RSA");
//            //  cipher=  Cipher.getInstance("RSA",  new  BouncyCastleProvider());
//            cipher.init(Cipher.ENCRYPT_MODE,  publicKey);
//            byte[]  output  =  cipher.doFinal(plainTextData);
//            return  output;
//        }  catch  (NoSuchAlgorithmException  e)  {
//            throw  new  Exception("无此加密算法");
//        }  catch  (NoSuchPaddingException  e)  {
//            e.printStackTrace();
//            return  null;
//        }  catch  (InvalidKeyException  e)  {
//            throw  new  Exception ("加密公钥非法,请检查");
//        } catch (IllegalBlockSizeException e) {
//            throw new Exception("明文长度非法");
//        } catch (BadPaddingException e) {
//            throw new Exception("明文数据已损坏");
//        }
//    }
}
