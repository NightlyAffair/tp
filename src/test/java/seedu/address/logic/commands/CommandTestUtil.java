package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROVIDER_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditPolicyDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    //Person test data
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_BIRTHDAY_AMY = "2002-02-09";
    public static final String VALID_BIRTHDAY_BOB = "2002-11-12";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_PREMIUM_AMY = "LifeShield " + 100;
    public static final String VALID_PREMIUM_BOB = "ElderShield " + 200;

    // Policy test data
    public static final String VALID_POLICY_NUMBER = "POL123";
    public static final String VALID_POLICY_NAME = "LifeShield";
    public static final String VALID_PROVIDER_COMPANY = "ShieldCorp";
    public static final String VALID_POLICY_LINK = "https://www.shieldcorp.com/policy123";
    public static final String VALID_POLICY_NUMBER_POL101 = "POL101";
    public static final String VALID_POLICY_NUMBER_POL456 = "POL456";
    public static final String VALID_POLICY_NAME_POL101 = "HomeSafe";
    public static final String VALID_POLICY_NAME_POL456 = "HealthPlus";
    public static final String VALID_PROVIDER_COMPANY_POL101 = "HomeGuard";
    public static final String VALID_PROVIDER_COMPANY_POL456 = "HealthCorp";
    public static final String VALID_POLICY_LINK_POL101 = "https://www.homeguard.com/policy101";
    public static final String VALID_POLICY_LINK_POL456 = "https://www.healthcorp.com/policy456";

    //existing person command descriptors
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String BIRTHDAY_DESC_AMY = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_AMY;
    public static final String BIRTHDAY_DESC_BOB = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String PREMIUM_DESC_AMY = " " + PREFIX_PREMIUM + VALID_PREMIUM_AMY;
    public static final String PREMIUM_DESC_BOB = " " + PREFIX_PREMIUM + VALID_PREMIUM_BOB;

    // Policy command descriptors
    public static final String POLICY_NUMBER_DESC_POL101 = " " + PREFIX_POLICY_NUMBER + VALID_POLICY_NUMBER_POL101;
    public static final String POLICY_NUMBER_DESC_POL456 = " " + PREFIX_POLICY_NUMBER + VALID_POLICY_NUMBER_POL456;
    public static final String POLICY_NAME_DESC_POL101 = " " + PREFIX_POLICY_NAME + VALID_POLICY_NAME_POL101;
    public static final String POLICY_NAME_DESC_POL456 = " " + PREFIX_POLICY_NAME + VALID_POLICY_NAME_POL456;
    public static final String PROVIDER_COMPANY_DESC_POL101 =
        " " + PREFIX_PROVIDER_COMPANY + VALID_PROVIDER_COMPANY_POL101;
    public static final String PROVIDER_COMPANY_DESC_POL456 =
        " " + PREFIX_PROVIDER_COMPANY + VALID_PROVIDER_COMPANY_POL456;
    public static final String POLICY_LINK_DESC_POL101 = " " + PREFIX_POLICY_LINK + VALID_POLICY_LINK_POL101;
    public static final String POLICY_LINK_DESC_POL456 = " " + PREFIX_POLICY_LINK + VALID_POLICY_LINK_POL456;

    //invalid person command descriptors
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_BIRTHDAY_DESC = " " + PREFIX_BIRTHDAY + "02-30-2000"; // Invalid date
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_PREMIUM_DESC = " " + PREFIX_PREMIUM + " asd " + -9999; // negative premium not
    // allowed

    // Invalid policy data
    public static final String INVALID_POLICY_NUMBER_DESC = " " + PREFIX_POLICY_NUMBER + "Invalid123";
    public static final String INVALID_POLICY_NAME_DESC = " " + PREFIX_POLICY_NAME + "Invalid$Name";
    public static final String INVALID_PROVIDER_COMPANY_DESC = " " + PREFIX_PROVIDER_COMPANY + "Invalid*Company";
    public static final String INVALID_POLICY_LINK_DESC = " " + PREFIX_POLICY_LINK + "invalid-link";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditPolicyCommand.EditPolicyDescriptor DESC_POLICY_POL101;
    public static final EditPolicyCommand.EditPolicyDescriptor DESC_POLICY_POL456;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withBirthday(VALID_BIRTHDAY_AMY).withPremiumList(VALID_PREMIUM_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withBirthday(VALID_BIRTHDAY_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withPremiumList(VALID_PREMIUM_BOB).build();
        DESC_POLICY_POL101 = new EditPolicyDescriptorBuilder().withPolicyNumber(VALID_POLICY_NUMBER_POL101)
                .withPolicyName(VALID_POLICY_NAME_POL101).withProviderCompany(VALID_PROVIDER_COMPANY_POL101)
                .withPolicyLink(VALID_POLICY_LINK_POL101).build();
        DESC_POLICY_POL456 = new EditPolicyDescriptorBuilder().withPolicyNumber(VALID_POLICY_NUMBER_POL456)
                .withPolicyName(VALID_POLICY_NAME_POL456).withProviderCompany(VALID_PROVIDER_COMPANY_POL456)
                .withPolicyLink(VALID_POLICY_LINK_POL456).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
