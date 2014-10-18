//
//  WelcomeViewController.m
//  WelcomeScreenSymHack
//
//  Created by Personal on 8/7/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import "WelcomeViewController.h"

@implementation WelcomeViewController

-(IBAction)showPicture:(NSButton *)sender
{
    NSString* tmpString = @"/Users/Matas/Desktop/test.jpg";
    NSImage *temp = [[NSImage alloc] initWithContentsOfFile:@"test.jpg"];
    [self.imageTaken setImage:temp];
    
    
    if(self.imageTaken)
    {
        self.alert = [[NSAlert alloc] init];
        [self.alert addButtonWithTitle:@"No"];
        [self.alert addButtonWithTitle:@"Yes"];
        [self.alert setMessageText:@"Image uploaded successfully. Are you sure you want to use this picture?"];
        [self.alert setAlertStyle:NSWarningAlertStyle];
        self.hasAcceptedFace = [self.alert runModal];
        
        if(self.hasAcceptedFace == NSAlertFirstButtonReturn)
        {
            [self.imageTaken setImage:nil];
        }
        
        else
        {
            NSLog(@"We would proceed now...");
        }
        
        
    }
    
    else
    {
        [self.label setStringValue:@"Image upload failed..."];
    }
}

@end
