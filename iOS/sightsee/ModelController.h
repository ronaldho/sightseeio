//
//  ModelController.h
//  sightsee
//
//  Created by Ronald Ho on 2014-10-17.
//  Copyright (c) 2014 Awesome Itinerary. All rights reserved.
//

#import <UIKit/UIKit.h>

@class DataViewController;

@interface ModelController : NSObject <UIPageViewControllerDataSource>

- (DataViewController *)viewControllerAtIndex:(NSUInteger)index storyboard:(UIStoryboard *)storyboard;
- (NSUInteger)indexOfViewController:(DataViewController *)viewController;

@end

