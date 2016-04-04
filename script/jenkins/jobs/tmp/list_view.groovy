listView('Listview') {
    jobs {
        regex('0' + '(.*?)')
    }
    columns {
        status()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}