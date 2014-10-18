//
//  TDOViewController.m
//  To-Do
//
//  Created by Jenny Bunny on 2014-10-18.
//  Copyright (c) 2014 Jenny Bunny. All rights reserved.
//

#import "TDOViewController.h"

@interface TDOViewController () <UIAlertViewDelegate>

@property (nonatomic) NSMutableArray *items;
@property (nonatomic) NSArray *categories;
@end

@implementation TDOViewController


- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    self.items = @[@{@"name" : @"Serious Biscuit", @"category" : @"Home"},
                   @{@"name" : @"Klondike", @"category" : @"Home"},
                   @{@"name" : @"Bustle", @"category" : @"Home"},
                   @{@"name" : @"The Six Gill", @"category" : @"Adventure"}
                   ].mutableCopy;
    
    self.categories = @[@"Home", @"Adventure"];
    self.navigationItem.title = @"To-do list";
    //adding a state dependent editing button

    //adding an add button
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(addNewItem:) ];
    self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"Edit" style:UIBarButtonItemStyleDone target:self action:@selector(addNewItem:)];
}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void) toggleEditing:(UIBarButtonItem *)sender{
    [self.tableView setEditing:!self.tableView.editing animated:YES];
    
    if(self.tableView.editing){
        sender.title = @"Done";
        sender.style = UIBarButtonItemStyleDone;
        
    }else{
        sender.title = @"Edit";
        sender.style = UIBarButtonItemStylePlain;
    }
}

                                  
#pragma mark - Datasource helper methods

- (NSArray *) itemsInCategory: (NSString *)targetCategory{
    NSPredicate *matchingPredicate = [NSPredicate predicateWithFormat:@"category == %@", targetCategory];
    NSArray *categoryItems = [self.items filteredArrayUsingPredicate:matchingPredicate];
    return categoryItems;
        
}
\


- (NSInteger) numberOfSectionsInCategory:(NSString *)targetCategory{
    return [self itemsInCategory:targetCategory].count;
    
}

-(NSDictionary *) itemAtIndexPath:(NSIndexPath *)indexPath{
    NSString *category= self.categories[indexPath.section];
    NSArray *categoryItems = [self itemsInCategory:category];
    NSDictionary* item = categoryItems[indexPath.row];
    
    return item;

}

//todo items no particular order

- (NSInteger) itemIndexForIndexPath: (NSIndexPath *) indexPath{
    NSDictionary *item = [self itemAtIndexPath:indexPath];
    NSInteger index = [self.items indexOfObjectIdenticalTo:item];
    
    return index;
}

- (void) removeItemsAtIndexPath:(NSIndexPath *) indexPath{
    NSInteger index = [self itemIndexForIndexPath:indexPath];
    [self.items removeObjectAtIndex:index];
}

#pragma mark - Adding items

- (void) addNewItem:(UIBarButtonItem *)sender{
    UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"New todo item" message:@"Please enter the name of the new todo item" delegate:self cancelButtonTitle:@"Cancel" otherButtonTitles:@"Add item", nil];
    alertView.alertViewStyle = UIAlertViewStylePlainTextInput;
    [alertView show];
}
                                             
                                             

-(void) alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    // if its not cancel button
    if(buttonIndex != alertView.cancelButtonIndex){
        UITextField * itemNameField= [alertView textFieldAtIndex:0];
        NSString * itemName = itemNameField.text;
        NSDictionary *item = @{@"name": itemName, @"category" : @"Home"};
        [self.items addObject:item];
        
        NSInteger numHomeItems = [self numberOfSectionsInCategory:@"Home"];
        [self.tableView insertRowsAtIndexPaths:@[[NSIndexPath indexPathForRow:numHomeItems - 1 inSection:0]] withRowAnimation:UITableViewRowAnimationAutomatic];
    }
}

#pragma mark - Table view datasource

- (NSInteger) numberOfSectionsInTableView:(UITableView *)tableView{
    return self.categories.count;
}

- (NSInteger) tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [self numberOfSectionsInCategory: self.categories[section]];
}

- (UITableViewCell *) tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *CellIdentifier = @"TodoItemRow";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    NSDictionary *item = [self itemAtIndexPath:indexPath];
    cell.textLabel.text= item[@"name"];
    
    if ([item[@"Completed"] boolValue]) {
        cell.accessoryType = UITableViewCellAccessoryCheckmark;
    }else{
        cell.accessoryType = UITableViewCellAccessoryNone;

    }
    return cell;

}

-(NSString *) tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section{
    return self.categories[section];
}

#pragma mark - Jenny feel like it

- (void) tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    NSInteger index = [self itemIndexForIndexPath:indexPath];
    NSMutableDictionary *item = [self.items[index] mutableCopy];
    BOOL completed = [item[@"Completed"] boolValue];
    item[@"Completed"] = @(!completed);
    
    self.items[index] = item;
    
    UITableViewCell *cell = [tableView cellForRowAtIndexPath:indexPath];
    cell.accessoryType= ([item[@"Completed"] boolValue]) ? UITableViewCellAccessoryCheckmark : UITableViewCellAccessoryNone;
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

-(UITableViewCellEditingStyle) tableView:(UITableView *)tableView editingStyleForRowAtIndexPath:(NSIndexPath *)indexPath{
    return UITableViewCellEditingStyleDelete;
}

- (BOOL) tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath{
    return YES;
}

- (void) tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if(editingStyle == UITableViewCellEditingStyleDelete){
        [self removeItemsAtIndexPath:indexPath];
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationAutomatic];
    }
}

@end
