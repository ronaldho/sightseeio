//
//  HelloWorldAppDelegate.h
//  test_encryption
//
//  Created by Personal on 8/8/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import <Cocoa/Cocoa.h>

@class TestViewController;

@interface HelloWorldAppDelegate : NSObject <NSApplicationDelegate>

@property (assign) IBOutlet NSWindow *window;
@property(nonatomic,strong) TestViewController *testViewController;

@end
