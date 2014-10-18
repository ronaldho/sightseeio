//
//  Kleancryption.m
//  test_encryption
//
//  Created by Personal on 8/8/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import "Kleancryption.h"
#import "RNEncryptor.h"
#import "RNDecryptor.h"

@implementation Kleancryption

NSString *const CONFIRM_KEY = @"|Ÿ’zè√5·˛ µì√)˚M£nã~¯Œ1«u>á⁄Ωú‹ﬁ‹^)à·C+å‡∞aûúGS÷©x«H≤’tFÜÒ“;éRÅå?OÀ÷©2ÜıÎ]GG[ùËú|ƒû˛Ùì]‹ÍL!E[*H<åıÙ˝ΩÎÀ4´…º3™Ài{ÌôH»å+◊ÙªÎ1¶≈4sâw„˜çÇÃ}*Z∞ÔrÌÜùåc`ÀcœßzU;˝D ‚7oGd¡‹?wúí=®:¥wR!O:5ÁÁÎïÓ=Í›≠í∆‚Vev∆h*™†œ)Âm+MxòÕsçÌ’2H»<1‰ÇOJíÛWé!∂&";

+(void)encryptSingleFile:(NSString *)filepath andSendTo:(NSString *)dstFilePath
{
    NSError *error;
    NSData *encryptedData;
    
    if([[NSFileManager defaultManager] fileExistsAtPath:filepath])
    {
        NSData *data = [[NSFileManager defaultManager] contentsAtPath:filepath];
        
        encryptedData = [RNEncryptor encryptData:data
            withSettings:kRNCryptorAES256Settings
            password:CONFIRM_KEY
            error:&error];
    }
    
    if([[NSFileManager defaultManager]fileExistsAtPath:filepath])
    {
        [[NSFileManager defaultManager] createFileAtPath:dstFilePath contents:encryptedData attributes:nil];
        //[[NSFileManager defaultManager] removeItemAtPath:filepath error:&error];
    }
}

+(void)decryptSingleFile:(NSString *)filepath andSendTo:(NSString *)dstFilePath
{
    NSData *encryptedData;
    NSError *error;
    NSData *decryptedData;

    encryptedData = [[NSFileManager defaultManager] contentsAtPath:@"/Users/Matas/Desktop/test-encrypt.something"];
    decryptedData = [RNDecryptor decryptData:encryptedData
            withPassword:CONFIRM_KEY
            error:&error];
    
    NSString* tmp = [[NSString alloc] initWithData:decryptedData encoding:NSUTF8StringEncoding];
    [[NSFileManager defaultManager] createFileAtPath:@"/Users/Matas/Desktop/test-output.txt" contents:decryptedData attributes:nil];
    
    NSLog(@"data: %@", tmp);
    
    
}

@end
