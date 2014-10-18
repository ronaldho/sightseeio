//
//  socket_client.h
//  Socket_client
//
//  Created by Personal on 8/10/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import <Foundation/Foundation.h>
@class FastSocket;
@interface socket_client : NSObject

@property(nonatomic,strong) FastSocket *client;
-(long)sendFile:(NSString *)filename;
-(long)receiveFile:(NSString *)filename;


@end
