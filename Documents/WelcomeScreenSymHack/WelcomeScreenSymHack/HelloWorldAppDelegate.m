//
//  HelloWorldAppDelegate.m
//  WelcomeScreenSymHack
//
//  Created by Personal on 8/7/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import "HelloWorldAppDelegate.h"
#import "WelcomeViewcontroller.h"

@implementation HelloWorldAppDelegate

- (void)applicationDidFinishLaunching:(NSNotification *)aNotification
{
    NSRect windowFrame = [self.window frame];
    windowFrame.size.width = 595;
    windowFrame.size.height = 400;
    [self.window setFrame:windowFrame display:YES];
    
    // Insert code here to initialize your application
    self.welcomeViewController = [[WelcomeViewController alloc] initWithNibName:@"WelcomeViewController" bundle:nil];
    
    [self.window.contentView addSubview:self.welcomeViewController.view];
    self.welcomeViewController.view.frame = ((NSView*)self.window.contentView).bounds;
}

@end
