////  DayViewController.h//  sightsee////  Created by Ronald Ho on 2014-10-20.//  Copyright (c) 2014 Awesome Itinerary. All rights reserved.//#import <UIKit/UIKit.h>@interface ViewController : UIViewController <UITextFieldDelegate, UITableViewDelegate, UITableViewDataSource>@property (weak, nonatomic) IBOutlet UITableView *tblDayDetails;-(IBAction)refresh:(id)sender;@end