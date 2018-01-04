class EmailDsl {
    String toText
    String fromText
    String body

    /**
     * This method accepts a closure which is essentially the DSL. Delegate the
     * closure methods to
     * the DSL class so the calls can be processed
     */

    def static make(closure) {
        EmailDsls emailDsl = new EmailDsls()
        // any method called in closure will be delegated to the EmailDsls class
        closure.delegate = emailDsl
        closure()
    }

    /**
     * Store the parameter as a variable and use it later to output a memo
     */

    def to(String toText) {
        this.toText = toText
    }

    def from(String fromText) {
        this.fromText = fromText
    }

    def body(String bodyText) {
        this.body = bodyText
    }

    def send() {
        String.join(':', [this.fromText, this.toText, this.body])
    }
}

println EmailDsls.make({
    from 'a'
    to 'b'
    body '111111'
    send()
})
