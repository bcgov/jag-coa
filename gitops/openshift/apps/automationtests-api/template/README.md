## Templates to create openshift components related to jag-coa-tests api deployment

### Command to execute template
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod
   ``oc process -f jag-coa-tests.yaml --param-file=jag-coa-tests.env | oc apply -f -``


