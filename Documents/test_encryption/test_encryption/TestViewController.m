//
//  TestViewController.m
//  test_encryption
//
//  Created by Personal on 8/8/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import "TestViewController.h"
#import "RNEncryptor.h"
#import "RNDecryptor.h"
#import "Kleancryption.h"

@interface TestViewController ()

@end

@implementation TestViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Initialization code here.
    }
    return self;
}

-(IBAction)performEncryptionProcess:(NSButton *)sender
{
    [Kleancryption encryptSingleFile:@"/Users/Matas/Desktop/test.txt" andSendTo:@"/Users/Matas/Desktop/test-encrypt.something"];
    [Kleancryption decryptSingleFile:@"test" andSendTo:@"test"];
    
}

@end
