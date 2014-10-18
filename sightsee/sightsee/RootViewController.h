//
//  RootViewController.h
//  sightsee
//
//  Created by Ronald Ho on 2014-10-17.
//  Copyright (c) 2014 Awesome Itinerary. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface RootViewController : UIViewController <UIPageViewControllerDelegate>

@property (strong, nonatomic) UIPageViewController *pageViewController;

@end

