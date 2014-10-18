#import "ScaryBugData.h"

@implementation ScaryBugData

- (id)initWithTitle:(NSString*)title rating:(float)rating
{
    if ((self = [super init]))
    {
        self.title = title;
        self.rating = rating;
    }
    
    return self;
}

@end