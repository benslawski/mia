if __name__ == "__main__":

    all_emails = set([])
    bad_emails = set([])
    good_emails = set([])

    f = open('All Contacts.csv', 'r')
    for line in f:
        all_emails.add(line.strip())
    f.close()

    f = open('take out.csv', 'r')
    for line in f:
        bad_emails.add(line.strip())
    f.close()

    good_emails = all_emails - bad_emails
    f = open('outfile.csv', 'w')
    f.write('\n'.join(good_emails))
    f.close()

    print list(all_emails)[0:10]
    print list(bad_emails)[0:10]


