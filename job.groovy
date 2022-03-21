job('JOB-02-FREESTYLE-PROJECT-02-REDIS'){
    description('Get or set Redis key')

    parameters{
        choiceParam('Action', ['Getall', 'Get', 'Set'])
        stringParam('Key')
        stringParam('Value')
    }

    // scm{
    //     git('https://github.com/xcodephile/blabla', 'main')
    // }

    // triggers {
    //     cron('H 5 * * 7')
    // }

    wrappers {
        credentialsBinding {
            string('REDIS_PASSWORD', 'REDIS_PASSWORD')
        }
    }

    steps {
        wrappers {
            colorizeOutput(colorMap = 'xterm')
        }
        shell('''#!/bin/bash

echo "$Action $Key $Value"

if [[ "$Action" == "Getall" ]]; then
  redis-cli -h redis -p 6379 -a $REDIS_PASSWORD keys '*'
elif [[ "$Action" == "Get" ]]; then
  redis-cli -h redis -p 6379 -a $REDIS_PASSWORD get $Key
elif [[ "$Action" == "Set" ]]; then
  redis-cli -h redis -p 6379 -a $REDIS_PASSWORD set $Key $Value
fi
        ''')
    }
}