//
//  HelloWorldAppDelegate.h
//  WelcomeScreenSymHack
//
//  Created by Personal on 8/7/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import <Cocoa/Cocoa.h>
@class WelcomeViewController;
@interface HelloWorldAppDelegate : NSObject <NSApplicationDelegate>

@property (assign) IBOutlet NSWindow *window;
@property (nonatomic,strong) IBOutlet WelcomeViewController* welcomeViewController;
@end
