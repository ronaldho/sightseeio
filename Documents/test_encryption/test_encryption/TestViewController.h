//
//  TestViewController.h
//  test_encryption
//
//  Created by Personal on 8/8/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import <Cocoa/Cocoa.h>

@interface TestViewController : NSViewController

@property(nonatomic,strong) IBOutlet NSButton *encryptButton;

-(IBAction) performEncryptionProcess:(NSButton *)sender;
@end
