# Retail Customer Family Information

## ADDED Requirements

### Requirement: Family Information Display
The system SHALL display comprehensive family information for retail customers including basic demographics, financial status, and credit information in a structured card format.

#### Scenario: View family basic information
- **WHEN** user navigates to family information tab
- **THEN** system displays family member count, dependent count, labor force count, and children count
- **AND** system displays householder status and name
- **AND** system displays family harmony status and property ownership
- **AND** system displays family strength indicator

#### Scenario: View family financial information
- **WHEN** user views family information card
- **THEN** system displays annual income and income range with proper currency formatting
- **AND** system displays main income source from dictionary
- **AND** system displays annual expenditure and expenditure range
- **AND** system displays total assets and total liabilities
- **AND** system displays business information and scale

#### Scenario: View family credit information
- **WHEN** user views family information card
- **THEN** system displays credit family status (yes/no)
- **AND** system displays credit status from dictionary
- **AND** system displays credit amount with proper formatting
- **AND** system displays debt status from dictionary
- **AND** system displays adverse records if any exist

#### Scenario: View family residence information
- **WHEN** user views family information card
- **THEN** system displays residence status from dictionary
- **AND** system displays house status from dictionary
- **AND** system displays residence location description

#### Scenario: Handle missing family information
- **WHEN** customer has no family information
- **THEN** system displays empty state message
- **AND** system provides option to add family information

### Requirement: Family Member List Management
The system SHALL provide a comprehensive list view of all family members with detailed information and support both table and card display modes.

#### Scenario: Display family members in table view
- **WHEN** user selects table view mode
- **THEN** system displays family members in VxeTable with columns: name, relation, gender, age, identity number, education, company, position, mobile
- **AND** system formats gender as Male/Female from numeric code
- **AND** system translates relation and education from dictionary
- **AND** system paginates results if more than page size
- **AND** system provides sorting and filtering capabilities

#### Scenario: Display family members in card view
- **WHEN** user selects card view mode
- **THEN** system displays family members as individual cards in responsive grid
- **AND** each card shows member avatar based on gender
- **AND** each card shows name, relation, age, gender prominently
- **AND** each card shows masked identity number for privacy
- **AND** each card shows education, company, position, and contact info
- **AND** system applies blue gradient for male members
- **AND** system applies pink gradient for female members

#### Scenario: Toggle between view modes
- **WHEN** user clicks view mode toggle button
- **THEN** system switches between table and card views
- **AND** system persists selected view mode to localStorage
- **AND** system loads persisted view mode on next visit

#### Scenario: Refresh family member data
- **WHEN** user clicks refresh button
- **THEN** system reloads family member data from backend
- **AND** system updates the current view (table or card)
- **AND** system displays success/error message based on result

#### Scenario: Handle empty member list
- **WHEN** family has no members
- **THEN** system displays empty state with appropriate message
- **AND** system provides option to add members

### Requirement: Dictionary Data Integration
The system SHALL use dictionary service for all family-related enumeration values to support dynamic configuration and internationalization.

#### Scenario: Load family relation types
- **WHEN** system displays family member information
- **THEN** system retrieves relation types from dictionary type 'aicrm_family_relation'
- **AND** system displays translated labels (spouse, father, mother, child, etc.)

#### Scenario: Load education levels
- **WHEN** system displays member education information
- **THEN** system retrieves education levels from dictionary type 'aicrm_education_level'
- **AND** system displays translated labels (high school, bachelor, master, PhD, etc.)

#### Scenario: Load family financial dictionaries
- **WHEN** system displays family financial information
- **THEN** system retrieves income scope from 'aicrm_family_income_scope'
- **AND** system retrieves expenditure scope from 'aicrm_family_expenditure_scope'
- **AND** system retrieves income source from 'aicrm_income_source'
- **AND** system retrieves debt scope from 'aicrm_debt_scope'
- **AND** system displays all values with proper translations

#### Scenario: Load family status dictionaries
- **WHEN** system displays family status information
- **THEN** system retrieves residence status from 'aicrm_residence_status'
- **AND** system retrieves house status from 'aicrm_house_status'
- **AND** system retrieves credit status from 'aicrm_credit_status'
- **AND** system retrieves debt status from 'aicrm_debt_status'
- **AND** system retrieves family strength from 'aicrm_family_strength'

### Requirement: Data Formatting and Display
The system SHALL format financial data, personal information, and dates according to Chinese locale conventions.

#### Scenario: Format monetary values
- **WHEN** system displays monetary amounts
- **THEN** system formats numbers with Chinese locale (comma separators)
- **AND** system appends unit indicator (10k CNY) for amounts
- **AND** system displays '-' for null or undefined values

#### Scenario: Format boolean values
- **WHEN** system displays boolean flags
- **THEN** system translates true as 'Yes' and false as 'No'
- **AND** system displays '-' for null or undefined values

#### Scenario: Mask sensitive information
- **WHEN** system displays identity numbers
- **THEN** system masks middle characters for privacy
- **AND** system keeps first and last few characters visible
- **AND** system provides tooltip or modal to view full number if authorized

### Requirement: Responsive Layout
The system SHALL provide responsive layout that adapts to different screen sizes and devices.

#### Scenario: Display on desktop
- **WHEN** user views page on desktop (>1400px width)
- **THEN** card view displays 3 columns
- **AND** table view displays all columns

#### Scenario: Display on tablet
- **WHEN** user views page on tablet (768px - 1400px)
- **THEN** card view displays 2 columns
- **AND** table view enables horizontal scroll for overflow

#### Scenario: Display on mobile
- **WHEN** user views page on mobile (<768px)
- **THEN** card view displays 1 column
- **AND** table view enables horizontal scroll
- **AND** font sizes and spacing adjust for smaller screens

### Requirement: Page Integration
The system SHALL integrate family information tab into retail customer 360-degree view page as a dedicated section.

#### Scenario: Navigate to family information tab
- **WHEN** user clicks "Family Information" in customer detail sidebar
- **THEN** system loads family info page component
- **AND** system passes customer ID to component
- **AND** system displays family information card and member list

#### Scenario: Refresh family data from parent
- **WHEN** parent component triggers refresh
- **THEN** family info component reloads all data
- **AND** system updates both family card and member list

### Requirement: Error Handling
The system SHALL handle errors gracefully and provide clear feedback to users.

#### Scenario: Handle API request failure
- **WHEN** backend API request fails
- **THEN** system displays error message to user
- **AND** system logs error details for debugging
- **AND** system maintains current UI state without breaking

#### Scenario: Handle network timeout
- **WHEN** API request times out
- **THEN** system displays timeout message
- **AND** system provides retry option
- **AND** system does not block other UI interactions

#### Scenario: Handle invalid data
- **WHEN** backend returns invalid or malformed data
- **THEN** system displays fallback values ('-')
- **AND** system logs validation errors
- **AND** system continues rendering other valid data

### Requirement: Performance Optimization
The system SHALL optimize data loading and rendering for smooth user experience.

#### Scenario: Load family data efficiently
- **WHEN** user opens family information tab
- **THEN** system loads family basic info and member list in parallel
- **AND** system caches loaded data during session
- **AND** system displays loading indicators during fetch

#### Scenario: Optimize card view rendering
- **WHEN** card view displays many members (>20)
- **THEN** system loads members with pagination (100 per page)
- **AND** system uses virtual scrolling if needed
- **AND** system maintains smooth scrolling performance

#### Scenario: Persist user preferences
- **WHEN** user selects view mode preference
- **THEN** system saves preference to localStorage immediately
- **AND** system applies preference on next page load
- **AND** system handles localStorage quota exceeded gracefully
