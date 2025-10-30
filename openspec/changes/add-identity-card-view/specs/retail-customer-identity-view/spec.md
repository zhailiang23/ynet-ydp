# Retail Customer Identity View - Spec Delta

## ADDED Requirements

### Requirement: View Mode Switching

The system SHALL provide a toggle mechanism to switch between table view and card view modes for displaying customer identity information.

#### Scenario: User switches to card view
- **GIVEN** user is viewing the identity list in table view
- **WHEN** user clicks the card view button in the toolbar
- **THEN** the list SHALL display in card view mode
- **AND** the selection SHALL be saved to local storage
- **AND** the card view button SHALL be highlighted as active

#### Scenario: User switches back to table view
- **GIVEN** user is viewing the identity list in card view
- **WHEN** user clicks the table view button in the toolbar
- **THEN** the list SHALL display in table view mode
- **AND** the selection SHALL be saved to local storage
- **AND** the table view button SHALL be highlighted as active

#### Scenario: View mode persistence
- **GIVEN** user has selected card view mode
- **WHEN** user refreshes the page or navigates away and returns
- **THEN** the list SHALL still display in card view mode
- **AND** the card view button SHALL be highlighted as active

### Requirement: Card View Display

The system SHALL display customer identity information in a responsive card grid layout when card view mode is selected.

#### Scenario: Display identities in card view
- **GIVEN** customer has multiple identity records
- **WHEN** user views the identity list in card view mode
- **THEN** each identity SHALL be displayed as a card
- **AND** cards SHALL be arranged in a responsive grid (1 column on mobile, 2 columns on tablet, 3-4 columns on desktop)
- **AND** each card SHALL display certificate type, masked certificate number, issue date, expiry date, validity status, issuing authority, and default certificate badge

#### Scenario: Card displays certificate type with icon
- **GIVEN** an identity record with a specific certificate type
- **WHEN** displayed in card view
- **THEN** the card header SHALL show the certificate type name
- **AND** the card SHALL display an appropriate icon for the certificate type

#### Scenario: Card displays masked certificate number
- **GIVEN** an identity record with a certificate number
- **WHEN** displayed in card view
- **THEN** the certificate number SHALL be masked for privacy
- **AND** the masking SHALL follow the same rules as the table view

#### Scenario: Card displays validity status
- **GIVEN** an identity record with an expiry date
- **WHEN** displayed in card view
- **THEN** the card SHALL display a validity status badge
- **AND** the badge SHALL show "有效" (valid) in green if not expired
- **AND** the badge SHALL show "已失效" (expired) in red if expired
- **AND** the badge SHALL show "长期有效" (permanent) if expiry date is 9999-12-31

#### Scenario: Card displays default certificate badge
- **GIVEN** an identity record marked as primary
- **WHEN** displayed in card view
- **THEN** the card SHALL display a "默认证件" (default certificate) badge
- **AND** the badge SHALL be styled with green color

#### Scenario: Empty state in card view
- **GIVEN** customer has no identity records
- **WHEN** user views the identity list in card view mode
- **THEN** an empty state message SHALL be displayed
- **AND** the message SHALL indicate no certificates are available

### Requirement: Card View Actions

The system SHALL support setting a certificate as default from the card view.

#### Scenario: Set certificate as default from card
- **GIVEN** user is viewing identities in card view
- **AND** a certificate is not marked as default
- **WHEN** user clicks "设为默认" (set as default) button on a card
- **THEN** the certificate SHALL be set as primary
- **AND** the card SHALL display the "默认证件" badge
- **AND** other certificates SHALL have their primary status removed
- **AND** the list SHALL refresh to reflect the changes

#### Scenario: Default certificate already set
- **GIVEN** user is viewing identities in card view
- **AND** a certificate is already marked as default
- **WHEN** user clicks "设为默认" button on that card
- **THEN** an info message SHALL display "该证件已经是默认证件" (this certificate is already default)
- **AND** no changes SHALL be made

### Requirement: Responsive Card Layout

The system SHALL provide responsive grid layout for card view that adapts to different screen sizes.

#### Scenario: Desktop display
- **GIVEN** user is viewing on a desktop screen (>= 1024px width)
- **WHEN** identity list is in card view mode
- **THEN** cards SHALL be displayed in a 3-4 column grid
- **AND** cards SHALL have appropriate spacing between them

#### Scenario: Tablet display
- **GIVEN** user is viewing on a tablet screen (>= 768px and < 1024px width)
- **WHEN** identity list is in card view mode
- **THEN** cards SHALL be displayed in a 2 column grid
- **AND** cards SHALL resize to fit the available width

#### Scenario: Mobile display
- **GIVEN** user is viewing on a mobile screen (< 768px width)
- **WHEN** identity list is in card view mode
- **THEN** cards SHALL be displayed in a single column
- **AND** cards SHALL span the full available width

### Requirement: View Mode UI Integration

The system SHALL integrate view mode toggle buttons into the identity list toolbar.

#### Scenario: Toolbar displays view toggle buttons
- **GIVEN** user is on the identity list page
- **THEN** the toolbar SHALL display table view and card view toggle buttons
- **AND** the buttons SHALL be positioned in the toolbar alongside other controls
- **AND** the active view mode button SHALL be visually highlighted

#### Scenario: View transition animation
- **GIVEN** user switches between view modes
- **WHEN** the transition occurs
- **THEN** a smooth fade transition animation SHALL be applied
- **AND** the transition SHALL complete within 300ms
