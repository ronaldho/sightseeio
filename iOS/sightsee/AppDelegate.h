////  AppDelegate.h//  sightsee////  Created by Ronald Ho on 2014-10-17.//  Copyright (c) 2014 Awesome Itinerary. All rights reserved.//#import <UIKit/UIKit.h>@interface AppDelegate : UIResponder <UIApplicationDelegate>@property (strong, nonatomic) UIWindow *window;+(void)downloadDataFromURL:(NSURL *)url withCompletionHandler:(void(^)(NSData *data))completionHandler;@end