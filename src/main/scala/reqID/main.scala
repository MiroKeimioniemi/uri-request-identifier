package reqID

@main
def main(): Unit = {
  println(new RequestIdentifier().identifyRequest("visma-identity://login?source=severa"))                            // Valid input
  println(new RequestIdentifier().identifyRequest("visma-identity://confirm?source=netvisor&paymentnumber=102226"))   // Valid input
  println(new RequestIdentifier().identifyRequest("visma-identity://sign?source=vismasign&documentid=105ab44"))       // Valid input
  println(new RequestIdentifier().identifyRequest("non-visma-identity://login?source=severa"))                        // Invalid scheme
  println(new RequestIdentifier().identifyRequest("non-visma-identity://backdoor?documentid=severa"))                 // Invalid path
  println(new RequestIdentifier().identifyRequest("non-visma-identity://login?documentid=severa"))                    // Invalid param
  println(new RequestIdentifier().identifyRequest("non-visma-identity://login?source=123"))                           // Invalid param type
  println(new RequestIdentifier().identifyRequest("visma-identity://confirm?source=netvisor&paymentnumber=I0SSS6"))   // Invalid param type
}