//
//  ViewController.h
//  JSONItune
//
//  Created by Jenny Bunny on 2014-10-18.
//  Copyright (c) 2014 Jenny Bunny. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController <UITableViewDataSource, UITableViewDelegate, NSURLConnectionDataDelegate>

@property (weak, nonatomic) IBOutlet UITableView *myTableView;

- (IBAction)getTop11Button:(id)sender;

@end
