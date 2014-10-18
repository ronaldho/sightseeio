//
//  HelloWorldAppDelegate.h
//  ScaryBugsMac
//
//  Created by Personal on 8/7/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import <Cocoa/Cocoa.h>
@class MasterViewController;

@interface HelloWorldAppDelegate : NSObject <NSApplicationDelegate>

@property (assign) IBOutlet NSWindow *window;
@property (nonatomic,strong) IBOutlet MasterViewController *masterViewController;
@end
