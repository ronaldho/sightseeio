//
//  ViewController.m
//  JSONItune
//
//  Created by Jenny Bunny on 2014-10-18.
//  Copyright (c) 2014 Jenny Bunny. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
{
    //instance variables
    NSMutableData *webData;
    NSURLConnection *connection;
    NSMutableArray *array; // top 10 songs
}

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    [[self myTableView] setDelegate:self]; // giving my self the delegate
    [[self myTableView] setDataSource:self];
    array = [[NSMutableArray alloc] init];
    
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void) connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response
{
    [webData setLength:0]; // the whole thing
    
}

-(void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
    [webData appendData:data];
}

-(void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error
{
    NSLog(@"fail with error");
}


-(void)connectionDidFinishLoading:(NSURLConnection *)connection
{
    NSDictionary *allDataDictionary = [NSJSONSerialization JSONObjectWithData:webData options:0 error:nil];
    NSArray *businesses = [allDataDictionary objectForKey:@"businesses"];
    //NSArray *arrayOfEntry = [feed objectForKey:@"entry"];
    
    for (NSDictionary *business in businesses){
        NSString *label = [business objectForKey:@"name"];
               
        [array addObject:label];
    }
    [[self myTableView]reloadData];
}


- (IBAction)getTop11Button:(id)sender {
    //NSURL *url = [NSURL URLWithString:@"http://itunes.apple.com/us/rss/topalbums/limit=10/json"];
    NSURL *url = [NSURL URLWithString:@"https://dl.dropboxusercontent.com/u/3137747/mock.json"];

    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    connection = [NSURLConnection connectionWithRequest:request delegate:self];
    
    if(connection)
    {
        webData= [[NSMutableData alloc] init];
    }
    
    else{
        NSLog(@"OH no");
    }
}

-(NSInteger) numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

-(NSInteger) tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [array count];
}

-(UITableViewCell *) tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if(!cell){
        cell = [[UITableViewCell alloc]initWithStyle:UITableViewCellStyleDefault reuseIdentifier:CellIdentifier];
        
    }
    else{
    }
    
    cell.textLabel.text = [array objectAtIndex:indexPath.row];
    return cell;
}

@end
