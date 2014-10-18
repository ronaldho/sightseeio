//
//  main.m
//  Socket_client
//
//  Created by Personal on 8/10/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "socket_client.h"

int main(int argc, const char * argv[])
{
    socket_client *app = [[socket_client alloc] init];
    
    NSLog(@"%ld returned",[app sendFile:@"/Users/Matas/Desktop/send.jpg"]);
    return 0;
}

