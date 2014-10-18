//
//  Kleancryption.h
//  test_encryption
//
//  Created by Personal on 8/8/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Kleancryption : NSObject
{
    NSString const* confirmKey;
}



+(void)encryptSingleFile:(NSString *)filepath andSendTo:(NSString*)dstFilePath;

+(void)decryptSingleFile:(NSString *)filepath andSendTo:(NSString*)dstFilePath;
@end
