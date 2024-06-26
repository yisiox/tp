---
  layout: default.md
  title: "AssetBook Developer Guide"
  pageNav: 3
---

# AssetBook Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<box type="info" seamless>

**Note:** The lifeline for *Commands* in all Sequence Diagrams should end at the destroy marker (X) if they exist, but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S2-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S2-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S2-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFX UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S2-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S2-CS2103T-W12-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S2-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

<box type="info" seamless>

**Note:**
* `XYZCommand` refers to all `Command` classes i.e. `AddCommand`, `FindCommand`, etc.
* Not all `Command` classes utilize `ArgumentMultimap`, `ArgumentTokenizer` or `ParserUtil` classes.

</box>

---

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml"/>

All `Command` objects are created via the enum factory pattern. The following sequence diagram expands on the actual command
creation process encapsulated by `createCommandUsingFactory()` in the above diagram.
Note that the leading whitespace in `" 1"` is an implementation detail of `AddressBookParser`.

<puml src="diagrams/CommandFactorySequenceDiagram.puml"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn uses an enum factory `CommandType` to create the relevant enum (e.g. `CommandType.DELETE`) from the parsed command word.
1. The `CommandType` enum calls the relevant command's factory method to create a command object (e.g. `DeleteCommand`) with the parsed arguments.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is returned back from `Logic` as a `String`.

How the parsing works:

1. The `AddressBookParser` passes the first token to `CommandType` which dispatches the matching type of command.
2. `AddressBookParser` instantiates the command with the user input arguments.
3. The command object (e.g. `DeleteCommand`) uses `ArgumentTokenizer`, `ArgumentMultimap` and possibly `ParserUtil` to break down and interpret the user input arguments.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S2-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e. all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g. results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores previous states of the `UniquePersonList` in a private `undoStack`.
* stores previously undone states of the `UniquePersonList` in a private `redoStack`.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)


<box type="info" seamless>

**Note:**

* More details on the implementation of `undo` and `redo` can be found [here](#undo-redo-feature).

* An alternative (arguably, more OOP) model is given below. It has a `Tag` list and an `Asset` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, and one `Asset` object per unique asset, instead of each `Person` needing their own `Tag` and `Asset` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S2-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Some classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Asset feature

Asset related functionality is supported by `Asset` and `Assets` objects. `Assets` are the actual field belonging to
a `Person` object, and may contain zero or more `Asset` objects.

The following class diagram details the relationship between classes involved under the model component.

<puml src="diagrams/AssetClassDiagram.puml"/>

`Assets` and `Asset` objects are immutable and expose a static factory `of` method which parses `String` objects to
return the respective class. To support editing a `Person` object's `Assets`, the `Assets` class has a static `edit` 
method which facilitates creating a new `Assets` object with a specified `Asset` object replaced.

Assets are created when the user invokes the `add`, `edit` or `asset` commands specifying an asset is to be
associated with person.

The following sequence diagram shows how an `add` command specifying creating a contact responsible for a 'pen'
asset interacts with the classes in `Logic` and `Model`.

<puml src="diagrams/AddSequenceDiagram.puml"/>

The follow sequence diagram expands on the process of creating a person with assets referenced in the above diagram.

<puml src="diagrams/PersonWithAssetsSequenceDiagram.puml"/>

The edit asset mechanism is implemented by the following operations:

1. Search through the entire `persons` list.
2. Look through the `assets` list of each `person`.
3. If the `assetName` is equivalent to the name from the `o\` prefix, rename it to the `assetName` specified by the `n\` prefix. If the `assetName` does not match, do nothing.

**Note:** If the `assetName` specified by the `o\` prefix does not exist in the user input, the application will throw an error to inform the user that the command is invalid.

#### Design Considerations

**Aspect: How users create and update assets**
* **Alternative 1**: The user specifies only the serial no. of the asset in `add`.
  The user uses the `asset` command to add or edit details to each asset.
  * Pros:
    * Each asset is unambiguously and uniquely identified on creation.
    * The user cannot make mistakes such as creating the same asset twice with different details.
  * Cons:
    * The user must use two separate commands to add an asset with details.
</li>

* **Alternative 2 (selected choice)**: The user specifies all details of the asset in `add`. Assets only have a name.
  * Pros:
    * Advanced users save time as only a single command is required to specify all details of multiple assets.
    * There is no possibility of data inconsistency.
  * Cons:
    * There is a lot of room for users to make mistakes such as creating the same asset twice unintentionally.
      This leads to the need to decide how best to handle each error. Throwing errors may frustrate the user while 
      making a guess of the user's intention may result in unintended changes made to the contacts.
    * The onus is on the user to uniquely name similar but distinct assets.
</li>

* **Alternative 3**: Have a dedicated set of commands to create and edit assets.
  * Pros:
    * The person related commands are not overloaded with the functionality to control assets.
    * The user is less likely to make a mistake as there is a clear distinction between persons and assets.
  * Cons:
    * Harder to implement, a second set of commands is essentially required.
    * Not the focus of the application, which is contact management.
</li>

* **Alternative 4**: Repurpose the existing tags feature as assets.
  * Pros:
    * Little implementation work and lower chance of bugs.
    * Less clutter in information presented to the user.
  * Cons:
    * Limited flexibility in extending the feature.
    * Users lose the ability to tag contacts which is a natural feature to have.
</li>

**Aspect: How updating of assets is implemented**
* **Alternative 1 (selected choice)**: All `Assets` and `Asset` objects are immutable; a linear search and replace
  is performed to update the `UniquePersonList` whenever a change to any is required.
  * Pros:
    * Undo is easy to implement and unlikely to have bugs.
  * Cons:
    * More work is required per operation to update assets. This is offset by the fact that considerable time by
      proportion is already invested in saving after each operation, so this extra time per operation is not as
      significant.
    * More likely to have data inconsistency bugs.
</li>

* **Alternative 2**: `Asset` has a static hash table with some primary key.
  * Pros:
    * All persons sharing an asset will have it represented by the same object in memory, making it easy to update.
    * Less likelihood of data inconsistency bugs.
  * Cons:
    * This design is not immutable, meaning undo is excessively difficult to implement.
</li>

### Find feature

The `PersonMatchesQueryPredicate` class defines the algorithm that determines whether a `Person` matches the user's `query` string.

The following sequence diagram shows how the `command` object is created when the user executes `find David`. Note that the leading whitespace in `" David"` is an implementation detail of `AddressBookParser`.

<puml src="diagrams/FindSequenceDiagram1.puml"/>
<br><br>

The _filtered_ list of `Person` objects in `model` is then updated as such:
<puml src="diagrams/FindSequenceDiagram2.puml"/>
<br><br>

### Undo/Redo feature

The undo/redo mechanism is implemented within `AddressBook.java` by saving the entire `persons` list. It uses an undo and a redo stack to maintain the history. Additionally, it implements the following operations:

* `AddressBook#save()` — Copies the current `persons` list into the `undoStack`.
* `AddressBook#undo()` — Restores the previous `persons` list state from the `undoStack`.
* `AddressBook#redo()` — Restores a previously undone `persons` list state from the `redoStack`.

`save()` is used within the `AddressBook` class methods, saving only when the persons list is about to be modified. `save()` is set to be private to prevent potential misuse from other classes, and Law of Demeter violations.

`undo` and `redo` are exposed in the `Model` interface as `Model#undo()`, `Model#redo()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `persons` list will be initialized with the initial address book state (State 0), with the `undoStack` and `redoStack` empty.

<puml src="diagrams/UndoRedoState0.puml"/>

Step 2. The user executes the command `delete 5` to delete the 5th person in the address book. The `delete` command eventually calls `save()`, which causes the state of the `persons` list **before** `delete 5` is executed (State 0) to be saved into the `undoStack`. The 5th person is then removed from the `persons` list (State 1).

<puml src="diagrams/UndoRedoState1.puml"/>

Step 3. The user executes `add n/David ...` to add a new person. The `add` command eventually calls `save()`, causing another `persons` list state (State 1) to be saved into the `undoStack`, before adding the person (State 2).

<puml src="diagrams/UndoRedoState2.puml"/>

<box type="info" seamless>

**Note:** If a command fails its execution, or if the command does not modify the `persons` list, it will not call `save()`. Hence, this `persons` list state will not be saved into the `undoStack`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undo()`, which will:
1. Copy the `persons` list (State 2) into the `redoStack`.
2. Pop the latest `persons` list state (State 1) from the `undoStack`.
3. Copy this popped state (State 1) into the `persons` list.

Notice that the states are copied into the `persons` list instead of replacing it, resulting in the exact same object being used. This is to prevent synchronization issues and to reduce coupling with the GUI, allowing the GUI to use this same list object throughout the program's life.

<puml src="diagrams/UndoRedoState3.puml"/>


<box type="info" seamless>

**Note:** If the `undoStack` is empty, then there are no previous `persons` list states to restore. The `undo` command uses `Model#canUndo()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml"/>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml"/>

The `redo` command does the opposite — it calls `Model#redo()`, which will:
1. Copy the `persons` list into the `undoStack`.
2. Pop the latest `persons` list state from the `redoStack`.
3. Copy this popped state into the `persons` list.

<box type="info" seamless>

**Note:** If the `redoStack` is empty, then there are no previously undone `persons` list states to restore. The `redo` command uses `Model#canRedo()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the `persons` list, such as `list`, will usually not call `AddressBook#save()`, `Model#undo()` or `Model#redo()`. Thus, the `undoStack` and `redoStack` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml"/>

Step 6. The user executes `clear`, which eventually calls `save()`.
Since there are still states in the `redoStack`, all states in the `redoStack` (State 2) will be removed.

Reason: It no longer makes sense to redo the `add n/David ...` command (State 2), and ignore the `clear` command (State 3). This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml"/>

The following activity diagram summarizes what happens when a user executes a new command (excluding undo & redo):

<puml src="diagrams/SaveActivityDiagram.puml" width="250" />
<br><br>

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (selected choice):** Save the entire address book as objects.
    * Pros:
      * Easy to implement.
      * Unlikely to have bugs.
    * Cons:
      * May have performance issues in terms of memory usage.
</li>

* **Alternative 2:** Save the entire address book into storage as JSON files.
    * Pros:
      * Saves history between different program launches.
    * Cons:
      * May have performance issues with frequent storage access.
      * Increases coupling as `Model` will now need a reference to `Storage`.
</li>

* **Alternative 3:** Save the entire address book as a JSON `String` in RAM.
    * Pros:
      * Reduces memory footprint as only 1 String is used as compared to a many objects per Person.
      * Faster than JSON files as there are no accesses to storage.
    * Cons:
      * May have performance issues as it needs to be deserialized each time.
</li>

* **Alternative 4:** Each command implements their own specific `undo()` and `redo()` methods
    * Pros:
      * Will use less memory (e.g. for `delete`, just save the person being deleted).
      * Best performance.
    * Cons:
      * We must ensure that the implementation of each command's respective `undo` command is correct.
        This would be especially difficult for commands that modify multiple people at once (e.g. `asset` command)
</li>

**Aspect: Data structure used to store undo & redo states:**

* **Alternative 1 (selected choice):** 2 Stacks.
    * Pros:
      * Easy to implement.
      * Simple data structure.
      * Easy to clear all redo states.
    * Cons:
      * May have performance issues if many redo states are cleared at once.
</li>

* **Alternative 2:** ArrayList, using pointers for current state and redo limit.
    * Pros:
      * Redo states no longer have to be cleared, as they are tracked by pointers and can be replaced at indexes as needed.
    * Cons:
      * Harder to implement.
      * `add()` and `set()` have to be used appropriately to prevent synchronization issues.
      * Pointers have to be carefully implemented.
</li>

* **Alternative 3:** LinkedList.
    * Pros:
      * Fast in dropping many nodes after a specified index.
      * Simple data structure.
    * Cons:
      * Time-consuming to implement: Unfortunately, the built-in LinkedList does not have a method to drop all nodes after a certain index.
        Hence a custom data structure would have to be implemented in order to have this feature.
        There will not be much benefits of using a LinkedList here otherwise.
</li>

### Command History feature

The `CommandHistory` class is an abstraction for a command history data structure.
It stores all command strings that were successfully executed by the user.

When the user executes a command successfully, `LogicManager` calls the `add()` method in `CommandHistory` to store the command string.

<puml src="diagrams/CommandHistoryAddSequenceDiagram.puml" />
<br><br>


`CommandExecutor` is an interface that has the methods `execute()`, `getPreviousCommandText()` and `getNextCommandText()`.
A private nested class `RecordedCommandExecutor` in `MainWindow` implements this interface.

When the user presses the `UP` key, a `KeyEvent` is generated by JavaFX, which is then handled by the `CommandBox` class through the `handleKeyPressed()` method.

The following sequence diagram shows the interaction between the classes when the user presses the `UP` key.
Note that `TextField` is a class in JavaFX. `CommandBox` has a `textField` attribute. 

<puml src="diagrams/CommandHistoryUpSequenceDiagram.puml" />
<br><br>

A similar interaction occurs when the user presses the `DOWN` key.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* logistics managers 
* has a need to keep track of a significant number of contacts and assets
* prefers desktop apps over other types
* prefers typing to other forms of input
* can type fast

**Value proposition**:

Logistics managers often have to keep track of many contacts such as
* PICs of inventory items, amenities or transport
* PoCs of departments, external suppliers or maintenance services

This is usually done with general purpose software like Microsoft Excel, which
may be cumbersome to use as they
* take up excessive storage
* are not optimised for typing only

Therefore, the application aims to deliver the following:
* manage contacts and associated assets faster than a typical mouse/GUI focused app
* easily annotate contacts and assets with details
* easily search for information by any category
* easily copy contact information to clipboard
* import/export data in an easy-to-view format
* is lightweight

---

### User stories

Priorities: High (must have): `* * *`, Medium (nice to have): `* *`, Low (unlikely to have): `*`

| Priority  | As a ...        | I want to ...                                                    | So that I can ...                                                                   |
|-----------|---------------|------------------------------------------------------------------|-------------------------------------------------------------------------------------|
| `* * *`   | user          | add new contacts and assets                                      | keep track of these details                                                         |
| `* * *`   | user          | delete contacts                                                  | update the list if a contact is no longer needed                                    |
| `* * *`   | user          | edit contacts and assets                                         | change details without recreating contacts, as there are too many details to re-add |
| `* * *`   | user          | easily view my existing contacts from the GUI                    | visually find the contacts I'm looking for                                          |
| `* * *`   | user          | list all contacts                                                | view all contacts at a glance                                                       |
| `* * *`   | user          | search contacts by any category (e.g. name, asset, etc.)         | easily find the relevant contact                                                    |
| `* * *`   | user          | see usage instructions                                           | refer to instructions and examples when I forget how to use certain commands        |
| `* * `    | user          | add tags to contacts                                             | categorize them according to my preferences and workflow                            |
| `* *`     | user          | quickly copy contact information (e.g. email) onto the clipboard | use the contact information immediately after finding it                            |
| `* *`     | user          | see no advertisements                                            | not be distracted from my tasks                                                     |
| `* *`     | user          | add secondary personnel associated with an asset                 | have a backup contact if the main person is unreachable                             |
| `* *`     | user          | toggle between light/dark theme                                  | customize the app to my preferences                                                 |
| `* *`     | user          | resize the app’s window                                          | easily use multiple apps at once                                                    |
| `* *`     | user          | add a profile picture to each contact                            | easily identify them                                                                |
| `* *`     | user          | easily search within the system even if I mistype a few words    | more easily locate relevant information                                             |
| `* *`     | new user      | view a drop-down suggestion of commands                          | efficiently navigate and utilize the app without extensive prior knowledge          |
| `* *`     | hurried user  | have commands even with extra whitespaces accepted               | not waste time retyping commands                                                    |
| `* *`     | advanced user | type shorter commands                                            | manage my contacts and assets quicker                                                               |
| `* *`     | advanced user | use keyboard shortcuts                                           | use the app more efficiently                                                        |
| `*`       | advanced user | add custom fields                                                | add more information to contacts                                                    |

---

### Use cases

(For all use cases below, the **System** is `AssetBook (AB)` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 - Add a contact**
**MSS**
1. User requests to add a contact.
2. User specifies details of the contact.
3. AB adds the contact.
4. AB shows a success message and details of the added contact.<br>
   Use case ends.

**Extensions**

<div class="step">2a. AB detects user input is invalid.</div>
<div class="sub-step">2a1. AB displays an error message.</div>
<div class="sub-step">2a2. User enters new input.</div>
<div class="sub-step">Steps 2a1-2a2 are repeated until user input is valid.</div>
<div class="sub-step">Use case resumes from step 3.</div>

--- {.dotted}

**Use case: UC2 - List contacts**
**MSS**
1. User requests to list contacts.
2. AB displays all contacts.
3. AB shows a success message to the user.<br>
   Use case ends.

--- {.dotted}

**Use case: UC3 - Search contacts**
**MSS**
1. User requests to search contacts.
2. User specifies details to search by.
3. AB displays all matching contacts.
4. AB shows a success message and the number of matching users.<br>
   Use case ends.

**Extensions**

<div class="step">2a. AB detects user input is invalid.</div>
<div class="sub-step">2a1. AB displays an error message.</div>
<div class="sub-step">2a2. User enters new input.</div>
<div class="sub-step">Steps 2a1-2a2 are repeated until user input is valid.</div>
<div class="sub-step">Use case resumes from step 3.</div>

--- {.dotted}

**Use case: UC4 - Edit contact**
**MSS**
1. User !!searches for contact (UC3)!!.
2. User requests to edit a contact.
3. User specifies the index of the contact and details to edit.
4. AB updates the contact.
5. AB shows a success message and details of the updated contact.<br>
   Use case ends.

**Extensions**

<div class="step">1a. User could not find contact.</div>
<div class="sub-step">1a1. User !!searches again (UC3)!!.</div>
<div class="sub-step">Step 1a1 is repeated until contact is found.</div>
<div class="sub-step">Use case resumes from step 2.</div>
<div class="step">3a. AB detects user input is invalid.</div>
<div class="sub-step">3a1. AB displays an error message.</div>
<div class="sub-step">3a2. User enters new input.</div>
<div class="sub-step">Steps 3a1-3a2 are repeated until user input is valid.</div>
<div class="sub-step">Use case resumes from step 4.</div>

--- {.dotted}

**Use case: UC5 - Edit assets**
**MSS**
1. User requests to edit an asset.
2. User specifies the old and new name for the asset.
3. AB updates all relevant assets.
4. AB shows a success message and details of the new asset.<br>
   Use case ends.

**Extensions**

<div class="step">2a. AB detects user input is invalid.</div>
<div class="sub-step">2a1. AB displays an error message.</div>
<div class="sub-step">2a2. User enters new input.</div>
<div class="sub-step">Steps 2a1-2a2 are repeated until user input is valid.</div>
<div class="sub-step">Use case resumes from step 3.</div>

--- {.dotted}

**Use case: UC6 - Delete contact**
**MSS**
1. User !!searches for contact (UC3)!!.
2. User requests to delete a contact.
3. User specifies the index of the contact to delete.
4. AB deletes the contact.
5. AB shows a success message and details of the deleted contact.<br>
   Use case ends.

**Extensions**

<div class="step">1a. User could not find contact.</div>
<div class="sub-step">1a1. User !!searches again (UC3)!!.</div>
<div class="sub-step">Step 1a1 is repeated until contact is found.</div>
<div class="sub-step">Use case resumes from step 2.</div>
<div class="step">3a. AB detects user input is invalid.</div>
<div class="sub-step">3a1. AB displays an error message.</div>
<div class="sub-step">3a2. User enters new input.</div>
<div class="sub-step">Steps 3a1-3a2 are repeated until user input is valid.</div>
<div class="sub-step">Use case resumes from step 4.</div>

--- {.dotted}

**Use case: UC7 - Undo command**
**MSS**
1. User requests to undo a command.
2. AB undoes the last modifying command.
3. AB shows a success message.<br>
   Use case ends.

**Extensions**

<div class="step">1a. AB detects there is no previous command to undo.</div>
<div class="sub-step">1a1. AB lets the user know there is no command to undo.</div>
<div class="sub-step">Use case ends.</div>

--- {.dotted}

**Use case: UC8 - Redo command**
**MSS**
1. User requests to redo an undone command.
2. AB redoes the command that was previously undone.
3. AB shows a success message.<br>
   Use case ends.

**Extensions**

<div class="step">1a. AB detects there is no previously undone command to redo.</div>
<div class="sub-step">1a1. AB lets the user know there is no undo command to redo.</div>
<div class="sub-step">Use case ends.</div>

--- {.dotted}

**Use case: UC9 - Clear all contacts**
**MSS**
1. User requests to clear all contact data.
2. AB clears all contact data.
3. AB shows a success message.<br>
   Use case ends.

--- {.dotted}

**Use case: UC10 - Add person to JSON file directly**
**MSS**
1. User adds a new person to the JSON file.
2. User runs the application.
3. AB reads the JSON file and shows the updated contact list.<br>
   Use case ends.

**Extensions**

<div class="step">2a. AB detects that the JSON file is invalid.</div>
<div class="sub-step">2a1. AB displays a warning and loads an empty address book.</div>
<div class="sub-step">Use case ends.</div>

---

### Non-Functional Requirements

#### Product Design
1. Target user is clearly narrowed down to logistics managers.
1. Majority of the target users are likely to find the app worth using.
1. Users with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1. Features should fit together cohesively.
1. Should be easy and intuitive for users to use, even for users with limited technical knowledge.

#### Codebase
1. Must follow CS2103/T coding standards and code quality guidelines.
1. Must demonstrate evidence of:
   * logging
   * exceptions
   * assertions
   * defensive coding
1. Should have Single Level of Abstraction Principle (SLAP) applied at a reasonable level.
1. Should not have any noticeable code duplication.
1. Should be easily extensible for new features.
1. Should have high level testability with good coverage.
1. Should have little to no bugs.

#### Program
1. Should be optimized for the target user (logistics managers).
1. Should work on any mainstream OS as long as it has Java `11` or above installed.
1. Should work without internet access.
1. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
1. Should not crash under typical usage.
1. Should not log or collect any unnecessary user data.
1. Response time for all commands and operations should be less than 1 second.

#### Documentation
1. The target user should understand how to use the product easily by reading the User Guide.
1. User Guide should have higher overall quality compared to AddressBook-Level3 (AB3).
1. A new team member should understand the product's internal design easily by reading the Developer Guide.
1. Developer Guide should have higher overall quality compared to AB3.

#### Project Management
1. Project should be done iteratively and incrementally.
1. Project should demonstrate good use of these GitHub mechanisms:
   * milestones
   * releases
   * issue tracker (with good task definition, assignment, and tracking)
   * PRs, and PR reviews
1. Project should demonstrate good use of version control.
1. Developers should attempt to use the forking workflow at least for the early stages of the project.
1. Developers should make good use of time buffers.

### Glossary

* **AddressBook**: The underlying system that AssetBook is built on
* **Asset**: An item of logistical significance, may be amenities or inventory
* **Clipboard**: The computer's storage for data that is copied and that can be retrieve with the paste command
* **Command**: Text that a user inputs to interact with the application
* **Command Line Interface (CLI)**: An interface where text commands are inputted by users to interact with the computer
* **Graphical User Interface (GUI)**: The visual display of an application through which a user interacts with the computer
* **Logistics Manager**: Anyone who manages inventory or amenities in a professional capacity
* **Mainstream OS**: Windows, Linux, MacOS
* **Person-In-Charge (PIC)**: A contact responsible for an asset
* **Point-of-Contact (PoC)**: A contact representing a responsible entity like a department or external business

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder.

   1. Double-click the jar file.<br>
      Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a contact

1. Adding a contact

    1. Test case: `add n\John Doe p\98765432 e\johnd@example.com a\311, Clementi Ave 2, #02-25 t\friends t\owesMoney A\screwdriver`<br>
       Expected: AssetBook displays a success message, and the newly added contact's details are shown.

    1. Test case: `add n\John Doe`<br>
       Expected: AssetBook displays an error stating invalid command format, and no contact is added.

    1. Other incorrect `add` commands to try: `add n\ p\ e\ a\311, Clementi Ave 2, #02-25 t\friends t\owesMoney A\screwdriver` <br>
       Expected: Similar to previous.

### Deleting a contact

1. Deleting a contact while all contacts are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message.

   1. Other incorrect `delete` commands to try: `delete`, `delete 0`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Saving data

1. Dealing with corrupted data file

   1. Modify data file into an invalid format. Eg. Removing fields of a contact, changing non-list fields into lists, etc.

   1. Open AssetBook. If AssetBook was already opened, close and reopen it.<br>
      Expected: AssetBook will detect that the data file is corrupted, warn the user that the data file could not be loaded and that entering a command would reset it, and start with an empty list.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancements**

Team size: 5

1. **Make `undo` and `redo` messages more descriptive**: Currently, `undo` and `redo` commands only display a success message without any additional information. In future versions, we will display the specific commands undone or redone, along with the changes.
2. **Make lists produced by the `copy` command more user-friendly**: Currently, using the `copy` command on assets and tags will include the square brackets associated with the list data structure, which are unnecessary for the users. In future versions, we will remove these square brackets when copying tags and assets.
3. **Allow searching by specific fields**: Currently, using the `find` command searches across all names, tags, and assets indiscriminately (e.g. searching `bell` may return someone who owns a bell asset and someone else who has the name Bell). We plan to allow users the options to limit the range of their searching either through new commands or specifying prefixes.
4. **Allow adding of more information to assets**: Currently, additional details cannot be added to assets, such as specifications, locations, etc. In future versions, more details can be added to assets to make the app more useful.
5. **Provide support for contacts with the same name**: Currently, using the `add` command or `edit` command on names is not allowed if another contact has the same name, but there may exist multiple people with the same name that one needs to keep track of. In future versions, we aim to improve duplicate person detection and potentially allow multiple contacts with the same name as long as other fields are different.
6. **Add sharper colour contrast between assets and tags**: Currently, tags and assets have a very similar color scheme, making it harder for users to differentiate them. In future versions, we will add more visual clarity tags and assets.
7. **Make duplicate checking more robust**: The application enforces distinct contacts, but the user may add the same contact twice unintentionally (e.g. entering `dave tan` and `Dave Tan`). We plan to make duplicate checking more robust and potentially warn the user first for such cases.
8. **Add a `remark` command**: At present users can only add additional information to contacts using tags. We plan to allow the user to add more detailed information like notes using a `remark` command.
9. **Add command suggestions**: Currently, there is no support for any auto-complete suggestions for commands, that would aid both experienced and new users. We plan to add an auto-complete feature, that would display what command the user is likely typing in a separate pop-up, the ability to press `tab` to quickly complete the command word, and what fields they are missing.
10. **Make phone number validation more robust**: Currently, we perform little validation on the phone number to allow for inputs such as `2555, 232` for the user's convenience. This however means that inputs such as `+++` would be accepted, which is unlikely to be the user's intention. We plan to improve phone number validation without restricting the user in the future.
