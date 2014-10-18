//
//  ViewController.m
//  JSONLabel
//
//  Created by Jenny Bunny on 2014-10-18.
//  Copyright (c) 2014 Jenny Bunny. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
@property (strong, nonatomic) IBOutlet UILabel *Label;

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self getPrice];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void) getPrice{
    NSURL *url = [NSURL URLWithString:@"https://coinbase.com/api/v1/prices/spot_rate"];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    [NSURLConnection sendAsynchronousRequest:request queue:[NSOperationQueue mainQueue] completionHandler:^(NSURLResponse *response, NSData *data, NSError *error) {
        
        if(error == nil){
            NSDictionary *spotPrice = [NSJSONSerialization JSONObjectWithData:data options:0 error:NULL];
            self.Label.text = [spotPrice objectForKey:@"amount"];
        }
    }];
    


     }
     
@end
