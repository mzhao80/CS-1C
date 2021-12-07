package stacks;

/**
 * Contains methods that tell StackList objects what to do for each action available in the browser.
 */
public class Navigator {
    /**
     * Current link.
     */
    private String currentLink;
    /**
     * StackList of previously navigated links.
     */
    private StackList<String> backLinks;
    /**
     * StackList of forward links.
     */
    private StackList<String> forwardLinks;
    /**
     * Flag to check if this is the first action taken.
     */
    private boolean notInitial;

    /**
     * Instantiates a Navigator object.
     */
    public Navigator() {
        currentLink = "";
        backLinks = new StackList<String>("Back");
        forwardLinks = new StackList<String>("Forward");
        notInitial = false;
    }

    /**
     * Sets the current link to the parameter passed in and updates all StackLists accordingly.
     *
     * @param linkName link to set the current link to.
     */
    public void setCurrentLink(String linkName) {
        if (notInitial) {
            backLinks.push(currentLink);
            currentLink = linkName;
            forwardLinks.clear();
        } else {
            currentLink = linkName;
            notInitial = true;
        }

    }

    /**
     * Goes to previous link and updates all StackLists accordingly.
     */
    public void goBack() {
        if (!backLinks.isEmpty()) {
            forwardLinks.push(currentLink);
            currentLink = backLinks.pop();
        } else {
            System.out.println("\nWARNING! No back links left.");
        }
    }

    /**
     * Goes to forward link and updates all StackLists accordingly.
     */
    public void goForward() {
        if (!forwardLinks.isEmpty()) {
            backLinks.push(currentLink);
            currentLink = forwardLinks.pop();
        } else {
            System.out.println("\nWARNING! No forward links left.");
        }
    }

    /**
     * Returns current link.
     *
     * @return current link.
     */
    public String getCurrentLink() {
        return currentLink;
    }

    /**
     * Returns StackList of all back links.
     *
     * @return StackList of all back links.
     */
    public StackList<String> getBackLinks() {
        return backLinks;
    }

    /**
     * Returns StackList of all forward links.
     *
     * @return StackList of all forward links.
     */
    public StackList<String> getForwardLinks() {
        return forwardLinks;
    }
}
