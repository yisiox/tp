---
  layout: default.md
  title: "AssetBook User Guide"
  pageNav: 3
---

# AssetBook User Guide

---

## Introduction

Welcome **logistics managers**! If you are someone who 

+ **Manages logistics and inventory** in a professional capacity,

+ Is a **fast typist**, tired of shuffling back and forth between your mouse and keyboard,

+ Is feeling **swamped by the tedium of contact management** on traditional office software...

Rejoice because *AssetBook* is here!

*AssetBook* helps to streamline the task of **tracking contacts and which logistical assets they are in charge of**, 
making your management tasks smoother than ever.

*AssetBook* is designed to:

+ **Digitally Organize Your Contacts**<br> Say goodbye to scattered contacts; now, have them all organized and accessible in one place.

+ **Effortlessly Track All Your Assets**<br> Keep tabs on who is responsible for every piece of equipment and every item in your inventory.

+ **Maximize Your Operational Efficiency**<br> Our application, combined with your swift keyboard skills, turns the chore of contact management into a seamless and satisfying process
  with convenient commands and shortcuts.

---

<div style="page-break-after: always;"></div>

## Using this Guide

This user guide is meant to provide guidance for using *AssetBook* and comprehensively document its features.
You can click on blue-colored hyperlinks like this link to the [table of contents](#table-of-contents) to jump to the 
referenced section immediately.
A [glossary](#glossary) is provided in case you encounter any unfamiliar terms.

<box type="info" seamless>
    Look out for icons like this, which may contain addition information, tips and warnings.
</box>

<box type="tip" seamless>
    Use the sidebar to the right to immediately jump to any section from anywhere on this page.
</box>

#### New Users

If this your first time using *AssetBook*, head over to the [quick start](#quick-start) to learn how to set up the 
application and get started.
After which, you can have a look at the [features](#features) of *AssetBook*, or simply follow this guide in order.

<box type="info" seamless>
    If you do not have much technical know-how, not to worry! We will guide you through step-by-step.
</box>

#### Experienced Users

If you are not a new user, and are just looking to refresh your memory, you can utilise the [table of contents](#table-of-contents) 
or skip to the [command summary](#command-summary).

---

<div style="page-break-after: always;"></div>

<!-- * Table of Contents -->
## Table of Contents
+ [Introduction](#assetbook-user-guide)
+ [Using this Guide](#using-this-guide)
+ [Table of Contents](#table-of-contents)
+ [Quick Start](#quick-start)
  + [Setting Up](#setting-up)
  + [Navigating the GUI](#navigating-the-gui)
  + [Tutorials](#tutorials)
+ [Features](#features)
  + [Modifying Contact Data](#modifying-contact-data)
    + [Adding a Contact](#adding-a-contact-add)
    + [Deleting a Contact](#deleting-a-contact-delete)
    + [Editing a Contact](#editing-a-contact-edit)
    + [Editing an Asset](#editing-an-asset-asset)
  + [Miscellaneous Shortcuts](#miscellaneous-shortcuts)
    + [Listing all Contacts](#listing-all-contacts-list)
    + [Finding Contacts](#finding-contacts-find)
    + [Undoing Commands](#undoing-commands-undo)
    + [Redoing Commands](#redoing-commands-redo)
    + [Clear all Contacts](#clear-all-contacts-clear)
    + [Navigating Command History](#navigating-command-history-and)
    + [Copy a Field from a Contact](#copy-a-field-from-a-contact-copy)
    + [Getting Help](#getting-help-help)
    + [Exiting the Application](#exiting-the-application-exit)
  + [Saving the data file](#saving-the-data-file)
  + [Editing the data file](#editing-the-data-file)
+ [Frequently Asked Questions](#frequently-asked-questions)
+ [Known Issues and Future Features](#known-issues-and-future-features)
+ [Command Summary](#command-summary)
+ [Glossary](#glossary)

---

<div style="page-break-after: always;"></div>

## Quick Start

### Setting Up

<box type="info" seamless>

The following instructions are for **Windows**, **MacOS** or **Linux**.

</box>

1. Ensure you have [Java 11](https://www.oracle.com/sg/java\technologies/javase\jdk11-archive-downloads.html) installed on your Computer.

2. Download the latest `assetbook.jar` from [here](https://github.com/AY2324S2-CS2103T-W12-3/tp/releases).

<p align="center">
    <img src="images/setup-1.png" style="width: 90%;">
</p>

3. Move the file into the folder where you want *AssetBook* to store the contact information. New users may simply
   create a folder on their desktop, then drag and drop `assetbook.jar` inside.

<p align="center">
    <img src="images/setup-2.png" style="width: 15%;">
    <img src="images/setup-3.png" style="width: 80%;">
</p>

4. To launch the application, double-click on the `assetbook.jar` file and a GUI as shown in the following section should appear. 
   Note that the application contains some sample data when launched for the first time. 

<p align="center">
    <img src="images/setup-4.png" style="width: 90%;">
</p>

<box type="tip" seamless>
    Having trouble? You may find the <a href="{{ baseUrl }}/UserGuide.html#faq">FAQ</a> useful.
</box>

<div style="page-break-after: always;"></div>

### Navigating the GUI

Here are the components of the GUI.

<p align="center">
   <img src="images/navigating-gui.png" style="width: 85%;">
</p>

1. **Command Input Box**
2. **Command Output Box**
3. **Contacts List**
4. **Contact Details**
5. **Tags**
6. **Assets**

<box type="tip" seamless>
You can resize the command output box by dragging the top edge.
</box>

---

### Tutorials

This section provides a walkthrough of common actions performed in *AssetBook*.

All actions are performed through typing specific text into the [command input box](#navigating-the-gui).
For detailed documentation of all available features, please refer to the [features](#features) section.

<box type="tip" seamless>

**Tips for Beginners**

- Always double-check the details you enter.
- If an action doesn’t work as expected, review the command format and your input.
- Practice makes perfect. Try adding a few contacts and assets to get comfortable with the commands.

</box>

<div style="page-break-after: always;"></div>

#### Adding Your First Contact

Let's get started by adding our first contact, John Doe, into AssetBook.
1. **Click on the Command Input Box**:
   Begin by locating and clicking into the [command input box](#navigating-the-gui) at the bottom of your application's interface.
<p align="center">
  <img src="images/tutorial-add-1.png" style="width: 90%;">
</p>

<div style="page-break-after: always;"></div>

2. **Adding Our First Contact, John**:
   Let's add your first contact, John, by copying the following command into the command input box:<br>
   `add n\John Doe p\98765432 e\johnd@example.com a\311, Clementi Ave 2, #02-25 t\friends t\owesMoney A\screwdriver`
<p align="center">
   <img src="images/tutorial-add-2.png" style="width: 90%;">
</p>

3. **Execute the Command**:
   After typing the command, simply press `Enter` to add the contact to AssetBook.

<div style="page-break-after: always;"></div>

4. **Confirmation**:
   Check for a confirmation message in the application to verify that John Doe has been added successfully.
<p align="center">
   <img src="images/tutorial-add-3.png" style="width: 90%;">
</p>

<div style="page-break-after: always;"></div>

#### Searching for Contacts
Now that you've successfully added John Doe to AssetBook, let's go through the steps to find him in the system.
1. **Click the Command Input Box**:
   First, click into the command input box in the AssetBook interface. This is where you'll enter your find command.

2. **Enter the Find Command**:
   Let's now find the contact, John, we just added by copying the following command:<br>
   `find John`
<p align="center">
    <img src="images/tutorial-find-1.png" style="width: 90%;">
</p>

3. **Execute the Command**:
   After typing the command, simply press `Enter` to find the contact in AssetBook.

4. **View the Results**:
   The application will display John Doe in the search results, along with the total count of entries matching 'John' found in the system.
<p align="center">
   <img src="images/tutorial-find-2.png" style="width: 90%;">
</p>

<div style="page-break-after: always;"></div>

#### Editing your Contact 
Lets update the email of our first contact added John.

1. **Click on the Command Input Box**: 
   Begin by locating and clicking into the command input box within the AssetBook interface.

2. **Find out what is John's Index**:
   Firstly, using the previous command Find, we can find out what index John contact is by seeing the number beside his contact. 
In our case he is located index 1.
<p align="center">
  <img src="images/tutorial-edit-1.png" style="width: 90%;">
</p>

<div style="page-break-after: always;"></div>

3. **Enter the Edit Command**: 
Let's say we want to edit John's email, thus we can do so by copying the following command:<br>
`edit 1 e\newemail@example.com`

<p align="center">
  <img src="images/tutorial-edit-2.png" style="width: 90%;">
</p>

4. **Execute the Command**
   Press `Enter` after typing in the command. This will update John's contact details in AssetBook.

<div style="page-break-after: always;"></div>

5. **Confirmation**
   Look for a confirmation message in AssetBook, indicating that John's email address has been successfully updated.
<p align="center">
   <img src="images/tutorial-edit-3.png" style="width: 90%;">
</p>

<div style="page-break-after: always;"></div>

#### Editing Assets
Let's update the assets already in our system.

1. **Click on the Command Input Box**:
   Start by clicking into the command input box located within the AssetBook interface.

2. **Enter the Edit Asset Command**:
   If you need to rename an asset, for example, changing "screwdriver" to "screw", use the following command:<br>
   `asset o\screwdriver n\screw`

<p align="center">
   <img src="images/tutorial-asset-1.png" style="width: 90%;">
</p>

3. **Execute the Command**:
   After entering the command, press `Enter`. This action will update the asset's details in AssetBook.

4. **Confirmation**:
   Wait for a confirmation message in AssetBook to ensure that the asset has been successfully updated.
<p align="center">
   <img src="images/tutorial-asset-2.png" style="width: 90%;">
</p>

---

<div style="page-break-after: always;"></div>

## Features

This section documents in detail all the features of *AssetBook*.

Most features of *AssetBook* come in the form of *commands*, which are *specific text inputs* that the user enters to
perform an action in the application. Features fall into two broad categories: those that allow the user to **modify
contact data** and those that are **miscellaneous shortcuts** for convenience.

Colored text that look like the following describe the format of a command.

`add n\NAME p\PHONE e\EMAIL a\ADDRESS [t\TAG]... [A\ASSET]...`

A valid input by the user corresponding to the above will be

`add n\John Doe e\johndoe@example.com a\574 Ang Mo Kio Ave 10 p\12345678 A\L293D`

<div style="page-break-after: always;"></div>

<box type="tip" seamless>

Command words are not case-sensitive, i.e. add = Add = ADD.

</box>

<box type="info" seamless>

**Notes about the notation used to describe commands**

* Here, `add` is the *command word*, signifying that this adds a new contact to the list of contacts.

* `n\`, `p\`, `e\`, etc. are *prefixes* of *parameters*, signifying the text after them is some data field of the contact.
  They are included in the text that the user should input.

* Text in bold like `NAME` are descriptions of what kind of text should be entered in its place.<br>
  e.g. `n\John Doe` should be entered in place of `n\NAME`.

* Items in square brackets are optional.<br>
  e.g. `n\NAME [t\TAG]` can represent `n\John Doe t\friend` or simply `n\John Doe`.

* Items followed by `...` indicates that it can be entered multiple times.<br>
  e.g. `[t\TAG]...` can represent `t\friend`, `t\friend t\colleague`, etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n\NAME p\PHONE`, `p\PHONE n\NAME` is also acceptable.

* Parameters not specified by the command format entered by the user will be ignored.<br>
  e.g. The format is `exit`, but the user enters `exit 123`. This will still be interpreted as `exit`.

</box>

After successful execution of a command, some feedback will appear in the [command output box](#navigating-the-gui).
If you enter something that the application did not expect or does not understand, often an *error* message will show
up instead.

<box type="warning" seamless>

**If you are using a PDF version of this document**, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

---

<div style="page-break-after: always;"></div>

## Modifying Contact Data

This section contains the details for **modifying commands**, which will modify the asset book.

### Adding a Contact: `add`

Adds a new contact to the system, with 0 or more assets associated with the contact.

Format: `add n\NAME p\PHONE e\EMAIL a\ADDRESS [t\TAG]... [A\ASSET]...`

<box type="tip" seamless>
A person can have any number of tags and assets (including 0).
</box>

#### Example
* Add a new contact associated with the asset `L293D`:<br> 
  `add n\John Doe e\johndoe@example.com a\574 Ang Mo Kio Ave 10 p\12345678 A\L293D`

<p align="center">
    <img src="images/example-add-before.png" style="width: 90%;">
</p>

<div style="page-break-after: always;"></div>

#### Success

If the example was executed successfully, the following message will appear:<br>
`New person added: John Doe; Phone: 12345678; Email: johndoe@example.com; Address: 574 Ang Mo Kio Ave 10; Tags: []; Assets: [[L293D]]`

<p align="center">
    <img src="images/example-add-after.png" style="width: 90%;">
</p>

<div style="page-break-after: always;"></div>

#### Failure

If the example was not executed successfully, the proper syntax of the `add` command will be shown to you instead.

<p align="center">
    <img src="images/example-add-after-fail.png" style="width: 90%;">
</p>

<div style="page-break-after: always;"></div>

#### Parameters
`NAME`
* Name of the contact.
* Case sensitive, i.e. john doe ≠ John Doe.
* Leading and trailing spaces are automatically removed.
* Multiple people with the same name are not allowed.

`PHONE`
* Phone number of the contact.
* Only digits, '+', '-', ',' and spaces are allowed.
* The phone number must contain at least 3 characters.

`EMAIL`
* Email of the contact.
* Must be in the format ‘local-part@domain’, and must be in a valid email format.

`ADDRESS`
* Address of the contact.

`TAG`
* Tag(s) to categorize the contact into.
* Only digits and alphabets are allowed.

`ASSET`
* Asset(s) associated with contact.
* Contact may be created first without assets, then have assets added later using the `edit` command.
* Case sensitive, i.e. NUS ≠ nus.
* Multiple assets can be specified at once. For example, a valid option is `A\asset1 A\asset2 A\asset3`.

<box type="info" seamless>

You may assign the same asset to multiple contacts, so remember to name different assets with unique names.

</box>

---

<div style="page-break-after: always;"></div>

### Deleting a Contact: `delete`

Delete a contact from the system by specifying its index.

Format: `delete INDEX`

#### Example

`delete 1` deletes the contact with index `1`.

* `INDEX` refers to the unique contact index shown in the GUI.

---

### Editing a Contact: `edit`

Edit existing contacts without recreating them.

Format: `edit INDEX [n\NAME] [p\PHONE] [e\EMAIL] [a\ADDRESS] [t\TAG]... [A\ASSET]...`

#### Example
`edit 1 e\newemail@example.com` edits the contact with id `1`, changing its email to `newemail@example.com`.

* Edits the contact with the specified `INDEX`. `INDEX` refers to the unique contact index shown in the GUI.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* You can remove all tags associated to the contact by typing `t\` without specifying any tags after it.
* You can remove all assets associated to the contact by typing `A\` without specifying any assets after it.

<box type="warning" seamless>

Edit **replaces** the current assets associated to a contact. It does **not** add to the list of associated assets.

</box>

---

<div style="page-break-after: always;"></div>

### Editing an Asset: `asset`

Edit existing assets without recreating them.

Format: `asset o\OLD_ASSET_NAME n\NEW_ASSET_NAME`

#### Example
`asset o\hammer n\screwdriver` edits the asset `hammer`, changing its name to `screwdriver`.

* `o\` refers to the old asset name, while `n\` refers to the new asset name.
* The asset will be renamed for all contacts that have this asset.

---

## Miscellaneous Shortcuts

This section contains the details for **non-modifying commands** and other useful shortcuts.

### Listing all Contacts: `list`

Displays all contacts.

Format: `list`

---

### Finding Contacts: `find`

Finds contacts by names, tags or assets.

Format: `find QUERY`

#### Example
`find John` searches all contact names, tags and assets for the query `John`.

* The query is case-insensitive.
* All whitespaces in both the query and fields will be ignored.
* Each field is individually checked against the query.
* A match is found if the query is a substring of the field being checked.

---

<div style="page-break-after: always;"></div>

### Undoing Commands: `undo`

Undoes the last modifying command.

Format: `undo`

#### Example
After incorrectly deleting the first contact with `delete 1`, it can be restored with `undo`.

* Modifying commands include: `add`, `delete`, `edit`, `asset`.
* All other commands are non-modifying.

---

### Redoing Commands: `redo`

Reverses the latest `undo` command.

Format: `redo`

#### Example
If an asset name was changed, after `undo` was executed to verify what the previous asset name was, `redo` can be used to revert to the new asset name.

<box type="warning" seamless>

After executing an `undo` command, you cannot `redo` this if another modifying command was executed.

</box>

---

<div style="page-break-after: always;"></div>

### Clear all Contacts: `clear`

Deletes all contacts.

Format: `clear`

<box type="tip" seamless>

If you unintentionally deleted all contacts, you can use the `undo` command to revert this.

</box>

---

### Navigating Command History: `↑` and `↓`

Use keyboard shortcuts to navigate the command history.

Press the `↑` arrow key to view the previous command.

Press the `↓` arrow key to view the next command.

<box type="warning" seamless>
Only successfully executed commands are saved in the command history.
</box>

---

### Copy a Field from a Contact: `copy`

Copies a specific field to your computer clipboard for ease of pasting elsewhere.

Format: `copy INDEX PREFIX`

#### Example
`copy 1 p\` copies the phone number of the first contact displayed onto the clipboard.

* `INDEX` refers to the unique contact index shown in the GUI.

#### Prefix Reference

| Prefix | Field   |
|--------|---------|
| `n\`   | Name    |
| `p\`   | Phone   |
| `e\`   | Email   |
| `a\`   | Address |
| `t\`   | Tags    |
| `A\`   | Assets  |

---

<div style="page-break-after: always;"></div>

### Getting Help: `help`

Displays a link to this user guide.

Format: `help`

---

### Exiting the Application: `exit`

Exits the application. Equivalent to pressing the cross in the top right corner.

Format: `exit`

---

### Saving the data file

*AssetBook*'s data is saved automatically after any command that changes the data. There is no need to save manually.

---

### Editing the data file

*AssetBook*'s data are saved automatically as a JSON file `[JAR file location]/data/assetbook.json`.<br>
Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, *AssetBook* will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the *AssetBook* to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

---

<div style="page-break-after: always;"></div>

## Frequently Asked Questions

**Q**: What if my device does not run a mainstream operating system (not Windows, MacOS or Linux)?<br>
**A**: *AssetBook* will work so long as Java 11 can be installed on your device.

**Q**: Must I specifically use Java 11?<br>
**A**: While it may work on other versions, *AssetBook* is meant to work on Java 11. We cannot guarantee that it works on versions other than Java 11.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and replace the JSON data file it creates with the JSON file from your previous *AssetBook* home folder.

---

<div style="page-break-after: always;"></div>

## Known Issues and Future Features

#### Multiple Screens
When using multiple screens, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen.
The remedy is to delete the `preferences.json` file created by the application before running the application again.

#### Name Validation
At present, names allow any special character except `\`. This is to allow the user flexibility to include uncommon names
that may include symbols such as `/`, `'` and `-`. As such, the application may accept all manner of gibberish for names.
A remedy is planned for the future to perform more robust checks on names.

#### Scrolling
The astute user will notice that ability to scroll with keyboard is missing from the application's features. This feature
will be implemented in an upcoming release as soon as possible.

#### More Asset Details
Some users may require greater detail about the assets to be recorded. We plan to add features to support adding more
details to assets such as serial number, location, etc.

---

<div style="page-break-after: always;"></div>

## Command Summary

Action           | Format                                                                        | Example
-----------------|-------------------------------------------------------------------------------|--- 
**Add**          | `add n\NAME p\PHONE e\EMAIL a\ADDRESS [t\TAG]... [A\ASSET]...`                | `add n\John Doe e\johndoe@example.com p\+12345678 A\L293D`
**Delete**       | `delete INDEX`                                                                | `delete 1`
**Edit contact** | `edit INDEX [n\NAME] [p\PHONE] [e\EMAIL] [a\ADDRESS] [t\TAG]... [A\ASSET]...` | `edit 1 e\newemail@example.com`
**Edit asset**   | `asset o\OLD_ASSET_NAME n\NEW_ASSET_NAME`                                     | `asset o\hammer n\screwdriver`
**List**         | `list`                                                                        | `list`
**Find**         | `find QUERY`                                                                  | `find John`
**Undo**         | `undo`                                                                        | `undo`
**Redo**         | `redo`                                                                        | `redo`
**Clear**        | `clear`                                                                       | `clear`
**Copy**         | `copy INDEX PREFIX`                                                           | `copy 1 p\`
**Exit**         | `exit`                                                                        | `exit`

---

<div style="page-break-after: always;"></div>

## Glossary

#### Asset
An item or amenity of logistical significance.

#### Clipboard
A place where text is stored in the computer, and can be typically retrieved with `right click -> paste`, `Ctrl+V`, or `Command+V`.

#### Command
A specific text input entered into the command input box to interact with *AssetBook*.

#### GUI
Graphical User Interface. The window that appears when an application is launched.

#### Operating System
Windows, MacOS and Linux are examples of operating systems.

#### Parameter
A detail that a command expects to be entered by the user. For example, name is a parameter of the `add` command.

#### Substring
