//
//  HelloWorldAppDelegate.m
//  test_encryption
//
//  Created by Personal on 8/8/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import "HelloWorldAppDelegate.h"
#import "TestViewController.h"

@implementation HelloWorldAppDelegate

- (void)applicationDidFinishLaunching:(NSNotification *)aNotification
{
    // Insert code here to initialize your application
    self.testViewController = [[TestViewController alloc] initWithNibName:@"TestViewController" bundle:nil];
    
    [self.window.contentView addSubview:self.testViewController.view];
    self.testViewController.view.frame = ((NSView *)self.window.contentView).bounds;
}

@end
