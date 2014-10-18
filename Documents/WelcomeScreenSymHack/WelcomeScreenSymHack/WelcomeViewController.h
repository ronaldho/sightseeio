//
//  WelcomeViewController.h
//  WelcomeScreenSymHack
//
//  Created by Personal on 8/7/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import <Cocoa/Cocoa.h>

@interface WelcomeViewController : NSViewController

@property (assign) NSInteger hasAcceptedFace;
@property (strong) NSAlert *alert;
@property (nonatomic,strong) IBOutlet NSTextField *label;
@property (nonatomic,strong) IBOutlet NSButton *picButton;
@property (nonatomic,strong) IBOutlet NSImageView *imageTaken;

-(IBAction)showPicture:(id)sender;
@end
