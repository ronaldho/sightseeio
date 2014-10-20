////  DayViewController.m//  sightsee////  Created by Ronald Ho on 2014-10-20.//  Copyright (c) 2014 Awesome Itinerary. All rights reserved.//#import "DayViewController.h"#import "AppDelegate.h"#import "ViewController.h"@interface DayViewController ()-(void)getLeJSON:(NSString*)city;@end@implementation DayViewController- (void)viewDidLoad {    [super viewDidLoad];            // Do any additional setup after loading the view.        // Make self the delegate and datasource of the table view.    self.tblDayDetails.delegate = self;    self.tblDayDetails.dataSource = self;        // Initially hide the table view.    self.tblDayDetails.hidden = YES;}- (void)didReceiveMemoryWarning {    [super didReceiveMemoryWarning];    // Dispose of any resources that can be recreated.}/*#pragma mark - Navigation// In a storyboard-based application, you will often want to do a little preparation before navigation- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {    // Get the new view controller using [segue destinationViewController].    // Pass the selected object to the new view controller.}*/-(void)getLeJSON:(NSString*)city{    NSLog(@"Field has value in getlejson: %@", city);    //    NSString *URLString = [NSString stringWithFormat:@"http://sitecio.herokuapp.com/requests/requests.json?loc=%@", city];    //    NSString *URLString = [NSString stringWithFormat:@"http://sitecio.herokuapp.com/requests/requests.json?loc=seattle"];        NSString *URLString = [NSString stringWithFormat:@"https://dl.dropboxusercontent.com/u/3137747/mock2.json"];        NSURL *url = [NSURL URLWithString:URLString];        [AppDelegate downloadDataFromURL:url withCompletionHandler:^(NSData *data) {        // Check if any data returned.        if (data != nil) {            // Convert the returned data into a dictionary.            NSError *error;            NSMutableDictionary *returnedDict = [NSJSONSerialization JSONObjectWithData:data options:kNilOptions error:&error];            //            NSArray *returnedDict = [NSJSONSerialization JSONObjectWithData:data options:kNilOptions error:&error];            NSLog(@"Before IF ELSE");            if (error != nil) {                NSLog(@"%@", [error localizedDescription]);            }            else{                NSLog(@"In Else");                                //                NSArray *results= [returnedDict objectForKey:@"businesses"];                //                self.jsonDictionary =[returnedDict objectAtIndex:0];                //                self.jsonDictionary = [returnedDict objectForKey:@"businesses"];                self.jsonDictionary = [[returnedDict objectForKey:@"geonames"] objectAtIndex:0];                NSLog(@"%@", self.jsonDictionary);                                                // Reload the table view.                NSLog(@"Reload data");                //                [self.tblDayDetails reloadData];                [self.tblDayDetails performSelectorOnMainThread:@selector(reloadData)                                                     withObject:nil                                                  waitUntilDone:NO];                [self viewDidLoad];                // Show the table view.                NSLog(@"before hidden");                self.tblDayDetails.hidden = NO;                NSLog(@"after hidden");            }                                    /*             // Set the country name to the respective label.             self.lblCountry.text = [NSString stringWithFormat:@"%@ (%@)", [self.countryDetailsDictionary objectForKey:@"countryName"], [self.countryDetailsDictionary objectForKey:@"countryCode"]];             */                                                        }    }];}#pragma mark - UITableView method implementation-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{    return 1;}-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{    return 7;}-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"cell"];    if (cell == nil) {        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:@"Cell"];        cell.accessoryType = UITableViewCellAccessoryNone;        cell.selectionStyle = UITableViewCellSelectionStyleNone;    }        switch (indexPath.row) {        case 0:            cell.detailTextLabel.text = @"Capital";            cell.textLabel.text = [self.jsonDictionary objectForKey:@"capital"];            break;        case 1:            cell.detailTextLabel.text = @"Continent";            cell.textLabel.text = [self.jsonDictionary objectForKey:@"continentName"];            break;        case 2:            cell.detailTextLabel.text = @"Population";            cell.textLabel.text = [self.jsonDictionary objectForKey:@"population"];            break;        case 3:            cell.detailTextLabel.text = @"Area in Square Km";            cell.textLabel.text = [self.jsonDictionary objectForKey:@"areaInSqKm"];            break;        case 4:            cell.detailTextLabel.text = @"Currency";            cell.textLabel.text = [self.jsonDictionary objectForKey:@"currencyCode"];            break;        case 5:            cell.detailTextLabel.text = @"Languages";            cell.textLabel.text = [self.jsonDictionary objectForKey:@"languages"];            break;        case 6:            cell.textLabel.text = @"Neighbour Countries";            cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;            cell.selectionStyle = UITableViewCellSelectionStyleDefault;            break;                    default:            break;    }        return cell;}-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{    return 60.0;}-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{    if (indexPath.row == 6) {        [self performSegueWithIdentifier:@"idSegueNeighbours" sender:self];    }}@end