
/**
 * 
 * An implementation of a Rovers mobility, radio and camera functionality
 * 
 * @author Michias Shiferaw
 *
 */

import java.util.*;

public class Rover{

  //Rover Functional Attributes
  public enum State { engineOff, vehiculeOn}public enum StateVehiculeOn { Null, park, neutral, accelerateforward, constantspeedforward, deaccelerateforward, turn, brake, deacceleratebackwards, acceleratebackwards, constantspeedbackwards,radio, cameraDrill }
  public enum StateVehiculeOnRadio { Null, radioOn, connectToBase, connectToNASAHeadquarters, disconnectToCommunication }
  public enum StateVehiculeOnCameraDrill { Null, cameraDrillOn, activate16mmCamera, activateTheMoonSeries, activateColorCamera, takePicture, activateDrill }
  private State state;private StateVehiculeOn stateVehiculeOn; private StateVehiculeOnRadio stateVehiculeOnRadio;private StateVehiculeOnCameraDrill stateVehiculeOnCameraDrill;private Rover.StateVehiculeOnCameraDrill prev = stateVehiculeOnCameraDrill;private String msg;
  public Rover(){setStateVehiculeOn(StateVehiculeOn.Null);setStateVehiculeOnRadio(StateVehiculeOnRadio.Null);setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.Null);setState(State.engineOff);}
  private Rover.StateVehiculeOn prev1 = stateVehiculeOn;
  private static int setTime(){
    Scanner sc=null;
    sc=new Scanner(System.in);
    int time=0;
    System.out.println("How long (in seconds) are you holding the pedals or buttons for?");
    time = sc.nextInt();
    sc = null;
    return time;
  }
  private String noChange(StateVehiculeOn state){return (" the buggy will remain at "+ stateVehiculeOn) +".";}private String noChange(StateVehiculeOnRadio state) {return (" the buggy will remain at " + stateVehiculeOnRadio) + ".";}private String noChange(StateVehiculeOnCameraDrill state) {return (" the buggy will remain at " + stateVehiculeOnCameraDrill) + ".";}private String noChange(State state) {return (" the buggy will remain at still, on the" + state) + " state.";}
  public String getStateFullName(){String answer = state.toString();if (stateVehiculeOn != StateVehiculeOn.Null) { answer += "." + stateVehiculeOn.toString(); }return answer;}public State getState(){return state;}public StateVehiculeOn getStateVehiculeOn(){return stateVehiculeOn;}
  public boolean turnKeyPressRightPedal(){
    boolean wasEventProcessed = false;
    if (state==state.engineOff){
      msg = ("Initially the buggy was still with the engine off and so the key was turned and the right pedal was pressed so,");
    } else{
      msg = ("Initially the buggy was on with the engine running and so the key was turned and the right pedal was pressed so,");
    }
    State aState = state;
    switch (aState){case engineOff:setState(State.vehiculeOn);exitStateVehiculeOn(); wasEventProcessed = true;break;
      default: msg+=noChange(stateVehiculeOn);
        
    }
    System.out.println(msg);
    return wasEventProcessed;}
    public boolean turnRadioOn() {
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.state + " state and the radio was turned on so,");
    State aState = state;
    switch (aState) {
    case engineOff:
      setStateVehiculeOn(StateVehiculeOn.radio);
      wasEventProcessed = true;
      break;
    default:msg+=noChange(state);
      
    }
System.out.println(msg);
    return wasEventProcessed;
  }

  public boolean turnRadioOff() {
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.stateVehiculeOnRadio  + " state and so the radio was turned off so,");
    StateVehiculeOnRadio aStateVehiculeOnRadio = stateVehiculeOnRadio;
    switch (aStateVehiculeOnRadio) {
    case disconnectToCommunication:
      exitState();
      setState(State.engineOff);
      wasEventProcessed = true;
      break;
    default:msg+=noChange(stateVehiculeOnRadio); 
    }
System.out.println(msg);
    return wasEventProcessed;}
  public boolean turnCameraDrillOn() {
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.state + " state and so the camera/drill was turned on so,");
    State aState = state;
    switch (aState) {case engineOff:setStateVehiculeOn(StateVehiculeOn.cameraDrill);wasEventProcessed = true;break;default:msg+=noChange(stateVehiculeOnCameraDrill);}System.out.println(msg);
    return wasEventProcessed;
  }
  public boolean pressLeftPedalTwice(){
    boolean wasEventProcessed = false;
    msg=("Initially the buggy was at "+ this.stateVehiculeOn + " and so the left pedal was pressed twice so,");
    StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    switch (aStateVehiculeOn){case park:setStateVehiculeOn(StateVehiculeOn.neutral);exitStateVehiculeOn(); wasEventProcessed = true;break;
    case acceleratebackwards:setStateVehiculeOn(StateVehiculeOn.deacceleratebackwards);exitStateVehiculeOn(); wasEventProcessed = true;break;
      default: msg+=noChange(stateVehiculeOn);}System.out.println(msg);
    return wasEventProcessed;}
  public boolean removeKey(){
    boolean wasEventProcessed = false;
    StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    msg=("Initially the buggy was at " + this.stateVehiculeOn + " and so the key was removed so,");
    switch (aStateVehiculeOn){case park:setState(State.engineOff);exitStateVehiculeOn(); wasEventProcessed = true;break;
      default: msg+=noChange(stateVehiculeOn);
    }System.out.println(msg +"\nEngine Off!");
    return wasEventProcessed;}

  public boolean pressRightPedal(){
    boolean wasEventProcessed = false;
    msg=("Initially the buggy was at " + this.stateVehiculeOn + " and so the right pedal was once so,");
    StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    switch (aStateVehiculeOn){case neutral:setStateVehiculeOn(StateVehiculeOn.park);exitStateVehiculeOn(); wasEventProcessed = true;break;
      default: msg+=noChange(stateVehiculeOn);}
    System.out.println(msg);
    return wasEventProcessed;
  }

  public boolean pressLeftPedalOnce(){
    boolean wasEventProcessed = false;
    msg=("Initially the buggy was at " + this.stateVehiculeOn + " and so the left pedal was pressed once so,");
    StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    switch (aStateVehiculeOn){case neutral:setStateVehiculeOn(StateVehiculeOn.accelerateforward);exitStateVehiculeOn(); wasEventProcessed = true;break;
      default: msg+=noChange(stateVehiculeOn);   
    }
    System.out.println(msg);
    return wasEventProcessed;
  }

  public boolean pressLeftPedal(){
    boolean wasEventProcessed = false;
    msg=("Initially the buggy was at " + this.stateVehiculeOn + " and so the left pedal was pressed so,");
    StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    switch (aStateVehiculeOn){case neutral:
      int timer=setTime();
      if (timer>=5){setStateVehiculeOn(StateVehiculeOn.acceleratebackwards);exitStateVehiculeOn(); wasEventProcessed = true;break;} else {msg += (" since you held it for " + timer + " seconds, the camera will remain at its current state");}break;
      default: msg+=noChange(stateVehiculeOn);}
    System.out.println(msg);
    return wasEventProcessed;  }
  public boolean pressLeftRightPedal()
  {
    boolean wasEventProcessed = false;
    msg=("Initially the buggy was at " + this.stateVehiculeOn + " and so the left pedal was pressed so,");
    StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    switch (aStateVehiculeOn){
    case accelerateforward:setStateVehiculeOn(StateVehiculeOn.brake);exitStateVehiculeOn(); wasEventProcessed = true;break;
    case constantspeedforward:setStateVehiculeOn(StateVehiculeOn.brake);exitStateVehiculeOn(); wasEventProcessed = true;break;
    case deaccelerateforward:setStateVehiculeOn(StateVehiculeOn.brake);exitStateVehiculeOn(); wasEventProcessed = true;break;
    case turn:setStateVehiculeOn(StateVehiculeOn.brake);exitStateVehiculeOn(); wasEventProcessed = true;break;
    case deacceleratebackwards:setStateVehiculeOn(StateVehiculeOn.brake);exitStateVehiculeOn(); wasEventProcessed = true;break;
    case constantspeedbackwards:setStateVehiculeOn(StateVehiculeOn.brake);exitStateVehiculeOn(); wasEventProcessed = true;break;
    default: msg+=noChange(stateVehiculeOn);
    }
    System.out.println(msg);
    return wasEventProcessed;
  }

  public boolean pressRightPedalTwice(){
    boolean wasEventProcessed = false;
    msg=("Initially the buggy was at " + this.stateVehiculeOn + " and so the right pedal was pressed twice so,");
    StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    switch (aStateVehiculeOn){case accelerateforward:setStateVehiculeOn(StateVehiculeOn.deaccelerateforward);exitStateVehiculeOn(); wasEventProcessed = true;break;
      default: msg+=noChange(stateVehiculeOn);   
    }
    System.out.println(msg);
    return wasEventProcessed;
  }
  public boolean pressRightPedalOnce(){
    boolean wasEventProcessed = false;
    msg=("Initially the buggy was at " + this.stateVehiculeOn + " and so the right pedal was pressed once so,");
    StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    switch (aStateVehiculeOn){
      case accelerateforward:
      int timer=setTime();
      if (timer>=5){
        setStateVehiculeOn(StateVehiculeOn.constantspeedforward);exitStateVehiculeOn(); wasEventProcessed = true;break;}else {msg += (" since you held it for " + timer + " seconds, the camera will remain at its current state");
      }break;
      case acceleratebackwards:timer=setTime();if (timer >= 5){setStateVehiculeOn(StateVehiculeOn.constantspeedbackwards);exitStateVehiculeOn(); wasEventProcessed = true;break;} else {msg += (" since you held it for " + timer + " seconds, the camera will remain at its current state");}break;
      default: msg+=noChange(stateVehiculeOn);
    }
    System.out.println(msg);
    return wasEventProcessed;
  }
  public boolean pressButton1OnceHoldDownBothPedals(){
    boolean wasEventProcessed = false;
    msg=("Initially the buggy was at " + this.stateVehiculeOn + " and so both pedals were pressed along with pushing button 1 once so,");
    StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    switch (aStateVehiculeOn){case constantspeedforward: prev1=stateVehiculeOn; setStateVehiculeOn(StateVehiculeOn.turn);exitStateVehiculeOn(); wasEventProcessed = true;break;
    case constantspeedbackwards:prev1 = stateVehiculeOn; setStateVehiculeOn(StateVehiculeOn.turn);exitStateVehiculeOn(); wasEventProcessed = true;break;default: msg+=noChange(stateVehiculeOn);}
    System.out.println(msg);
    return wasEventProcessed;
  }
  public boolean releaseRightPedal() {
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.stateVehiculeOn+ " and so the right pedal was released so,");
    StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    switch (aStateVehiculeOn) {
    case turn:if (prev1==StateVehiculeOn.constantspeedforward){setStateVehiculeOn(StateVehiculeOn.constantspeedforward);msg += ("the buggy is set back to driving forward at a constant speed.");
    } else if (prev1 == StateVehiculeOn.constantspeedbackwards) {setStateVehiculeOn(StateVehiculeOn.constantspeedbackwards);msg += ("the buggy is set back to driving backwards at a constant speed.");}wasEventProcessed = true;break;
    default: msg+=noChange(stateVehiculeOn);
    }
System.out.println(msg);
    return wasEventProcessed;
  }
  public boolean releasePedals(){boolean wasEventProcessed = false;msg=("Initially the buggy was at " + this.stateVehiculeOn + " and the pedals were released so, ");StateVehiculeOn aStateVehiculeOn = stateVehiculeOn;
    switch (aStateVehiculeOn){case brake:setStateVehiculeOn(StateVehiculeOn.neutral);exitStateVehiculeOn(); wasEventProcessed = true;break;default: msg+=noChange(stateVehiculeOn);}
    System.out.println(msg);
    return wasEventProcessed;}

  public boolean presseButton2Once() {
    boolean wasEventProcessed = false;
    msg=("Initially the buggy was at " + this.stateVehiculeOnRadio + " state and the pedals were released so, ");
    StateVehiculeOnRadio aStateVehiculeOnRadio = stateVehiculeOnRadio;
    switch (aStateVehiculeOnRadio) {
    case radioOn:
      exitStateVehiculeOnRadio();
      setStateVehiculeOnRadio(StateVehiculeOnRadio.connectToBase);
      wasEventProcessed = true;
      break;
    case disconnectToCommunication:
      exitStateVehiculeOnRadio();
      setStateVehiculeOnRadio(StateVehiculeOnRadio.connectToBase);
      wasEventProcessed = true;
      break;
    default:  msg+=noChange(stateVehiculeOnRadio);
    }
System.out.println(msg);
    return wasEventProcessed;
  }
  
  public boolean pressBothButtons() {
    boolean wasEventProcessed = false;
    StateVehiculeOnRadio aStateVehiculeOnRadio = stateVehiculeOnRadio;
    switch (aStateVehiculeOnRadio) {
    case radioOn:
    int timer=setTime();
      if (timer > 10) {
        exitStateVehiculeOnRadio();
        setStateVehiculeOnRadio(StateVehiculeOnRadio.connectToNASAHeadquarters);
        wasEventProcessed = true;
        break;
      } else {
        msg += (" since you held it for " + timer + " seconds, the camera will remain at its current state");
      }
      break;
    case disconnectToCommunication:
    timer=setTime();
      if (timer > 10) {exitStateVehiculeOnRadio();setStateVehiculeOnRadio(StateVehiculeOnRadio.connectToNASAHeadquarters);
        wasEventProcessed = true;break;
      } else {
        msg += (" since you held it for " + timer + " seconds, the camera will remain at its current state");}
      break;
    default: 
    }
    return wasEventProcessed;
  }

  public boolean pressButton2Twice() {
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.stateVehiculeOnRadio + " state and so the the second button was pressed twice so,");
    StateVehiculeOnRadio aStateVehiculeOnRadio = stateVehiculeOnRadio;
    switch (aStateVehiculeOnRadio) {
    case connectToBase:exitStateVehiculeOnRadio();setStateVehiculeOnRadio(StateVehiculeOnRadio.disconnectToCommunication);wasEventProcessed = true;break;
    case connectToNASAHeadquarters:exitStateVehiculeOnRadio();setStateVehiculeOnRadio(StateVehiculeOnRadio.disconnectToCommunication);wasEventProcessed = true;break;
    default:msg+=noChange(stateVehiculeOnRadio);}
    System.out.println(msg);return wasEventProcessed;}

  public boolean pressButton1(){
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.stateVehiculeOnCameraDrill
        + " state and so the first button was pressed so,");
    StateVehiculeOnCameraDrill aStateVehiculeOnCameraDrill = stateVehiculeOnCameraDrill;
    switch (aStateVehiculeOnCameraDrill)
    {
      case cameraDrillOn:
      int timer=setTime();
      if (timer==5)        {
          exitStateVehiculeOnCameraDrill();
          setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.activateColorCamera);
          wasEventProcessed = true;
          break;
        } else {
          msg += (" since you held it for " + timer + " seconds, the camera will remain at its current state");
        }
        break;
      case activate16mmCamera:
        prev = stateVehiculeOnCameraDrill;
      stateVehiculeOnCameraDrill=StateVehiculeOnCameraDrill.takePicture;
      exitStateVehiculeOnCameraDrill();
        setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.takePicture);
        wasEventProcessed = true;
        break;
      case activateColorCamera:
        prev = stateVehiculeOnCameraDrill;
        stateVehiculeOnCameraDrill = StateVehiculeOnCameraDrill.takePicture;
        exitStateVehiculeOnCameraDrill();
        setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.takePicture);
        wasEventProcessed = true;
        break;
      case activateDrill:
        exitState();
        setState(State.engineOff);
        wasEventProcessed = true;
        break;
      default:msg+=noChange(stateVehiculeOnCameraDrill);
        
    }
    System.out.println(msg);
    return wasEventProcessed;
  }

  public boolean pressButton1Twice() {
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.stateVehiculeOnCameraDrill + " state and so the first button was pressed twice so,");
    StateVehiculeOnCameraDrill aStateVehiculeOnCameraDrill = stateVehiculeOnCameraDrill;
    switch (aStateVehiculeOnCameraDrill) {
    case cameraDrillOn:
      exitStateVehiculeOnCameraDrill();
      setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.activateDrill);
      wasEventProcessed = true;
      break;
    default:msg+=noChange(stateVehiculeOnCameraDrill);
    }
System.out.println(msg);
    return wasEventProcessed;
  }

  public boolean holdButton1Once() {
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.stateVehiculeOnCameraDrill + " state and so the first button was pressed once so,");
    StateVehiculeOnCameraDrill aStateVehiculeOnCameraDrill = stateVehiculeOnCameraDrill;
    switch (aStateVehiculeOnCameraDrill) {
    case cameraDrillOn:
    int timer= setTime();
      if (timer == 10) {
        exitStateVehiculeOnCameraDrill();
        setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.activate16mmCamera);
        wasEventProcessed = true;
        break;
      }else{
        msg+=(" since you held it for "+ timer+" seconds, the camera will remain at its current state" );
      }
      break;
    default: msg+=noChange(stateVehiculeOnCameraDrill); 
    }
    System.out.println(msg);
    return wasEventProcessed;
  }

  public boolean holdButton1() {
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.stateVehiculeOnCameraDrill + " state and so the first button was held so,");
    StateVehiculeOnCameraDrill aStateVehiculeOnCameraDrill = stateVehiculeOnCameraDrill;
    switch (aStateVehiculeOnCameraDrill) {
    case activate16mmCamera:
      int timer = setTime();
      if (timer == 5) {prev=stateVehiculeOnCameraDrill;exitStateVehiculeOnCameraDrill();setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.activateTheMoonSeries);
        exitStateVehiculeOnCameraDrill();stateVehiculeOnCameraDrill=StateVehiculeOnCameraDrill.activateTheMoonSeries;
        wasEventProcessed = true;
        break;} else {
        msg += (" since you held it for " + timer + " seconds, the camera will remain at its current state");
      }
      break;
    case activateColorCamera:timer=setTime();
      if (timer == 5) {
        prev = stateVehiculeOnCameraDrill;exitStateVehiculeOnCameraDrill();
        setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.activateTheMoonSeries);
        wasEventProcessed = true;break;} else {
        msg += (" since you held it for " + timer + " seconds, the camera will remain at its current state");}break;
    default:msg+=noChange(stateVehiculeOnCameraDrill);   
    }
    System.out.println(msg);
    return wasEventProcessed;
  }
  public boolean pressButton2() {
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.stateVehiculeOnCameraDrill + " state and so the second button was pressed so,");
    StateVehiculeOnCameraDrill aStateVehiculeOnCameraDrill = stateVehiculeOnCameraDrill;
    switch (aStateVehiculeOnCameraDrill) {
    case activate16mmCamera:
      exitStateVehiculeOnCameraDrill();
      setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.cameraDrillOn);
      wasEventProcessed = true;
      break;
    case activateColorCamera:
      exitStateVehiculeOnCameraDrill();
      setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.cameraDrillOn);
      wasEventProcessed = true;
      break;
    case activateDrill:
      exitStateVehiculeOnCameraDrill();
      setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.cameraDrillOn);
      wasEventProcessed = true;
      break;
    default:msg+=noChange(stateVehiculeOnCameraDrill);
    }
    System.out.println(msg);
    return wasEventProcessed;
  }

  public boolean releaseButton1() {
    boolean wasEventProcessed = false;
    msg = ("Initially the buggy was at " + this.stateVehiculeOnCameraDrill + " state and so the first button was released so,");
    StateVehiculeOnCameraDrill aStateVehiculeOnCameraDrill = stateVehiculeOnCameraDrill;
    switch (aStateVehiculeOnCameraDrill) {
    case activateTheMoonSeries://exitStateVehiculeOnCameraDrill();
      if (prev==StateVehiculeOnCameraDrill.activate16mmCamera){setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.activate16mmCamera);msg+= ( "the camera is set back to the 16mm camera function");}else if (prev==StateVehiculeOnCameraDrill.activateColorCamera){setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.activateColorCamera);
        msg += ("the camera is set back to the color camera function");
      };
      wasEventProcessed = true;break;
      case takePicture:// exitStateVehiculeOnCameraDrill();
        if (prev == StateVehiculeOnCameraDrill.activate16mmCamera) {setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.activate16mmCamera);
          msg += ("the camera is set back to the 16mm camera function");} else if (prev == StateVehiculeOnCameraDrill.activateColorCamera) {setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.activateColorCamera);msg += ("the camera is set back to the color camera function");};
      wasEventProcessed = true;
      break;
    default:msg+=noChange(stateVehiculeOnCameraDrill);}
    System.out.println(msg);
    return wasEventProcessed;}
  private void exitState(){switch(state){case vehiculeOn:exitStateVehiculeOn();break;case engineOff:break;}}
  private void setState(State aState){state = aState;
    // entry actions and do activities
    switch(state){case vehiculeOn: if (stateVehiculeOn == StateVehiculeOn.Null) {setStateVehiculeOn(StateVehiculeOn.park); }break;
      case engineOff:}}
  private void exitStateVehiculeOn(){switch(stateVehiculeOn){
      case park:if(getState()==State.engineOff){msg+=(" the buggy is off. ");}else{
      setStateVehiculeOn(stateVehiculeOn);msg+=(" the buggy will be parked.");}break;
      case neutral:setStateVehiculeOn(stateVehiculeOn);msg+=(" the buggy will be in the neutral gear.");break;
      case accelerateforward:setStateVehiculeOn(stateVehiculeOn);msg+=(" the buggy will be accelerating forward.");break;
      case constantspeedforward:setStateVehiculeOn(stateVehiculeOn);msg+=(" the buggy will be moving at a constant speed forward:)");break;
      case deaccelerateforward: setStateVehiculeOn(stateVehiculeOn);msg+=(" the buggy will be slowing(decelerating) forward. ");break;
      case turn:setStateVehiculeOn(stateVehiculeOn);msg+=(" the buggy is turning :)");break;
      case brake:setStateVehiculeOn(stateVehiculeOn);msg+= ( " the buggy is braking.");break;
      case deacceleratebackwards:setStateVehiculeOn(stateVehiculeOn);msg+=(" the buggy will be slowing(decelerating) backwards.");break;
      case acceleratebackwards:setStateVehiculeOn(stateVehiculeOn);msg+=(" the buggy will be accelerating backwards.");break;
      case constantspeedbackwards:setStateVehiculeOn(stateVehiculeOn);msg+=(" the buggy will be moving at a constant speed backwards. ");break;
      case radio:setStateVehiculeOn(stateVehiculeOn);msg+=("BZZZ BZZZZ BZzzz radio is offline");break;
      case cameraDrill:setStateVehiculeOn(stateVehiculeOn);msg+=(" the camera/drill function is shutting downnnnnn.");break;
      default:break;}}
  private void setStateVehiculeOn(StateVehiculeOn aStateVehiculeOn){
    stateVehiculeOn = aStateVehiculeOn;
    if (state != State.vehiculeOn && aStateVehiculeOn != StateVehiculeOn.Null) {
       setState(State.vehiculeOn); }
     switch (stateVehiculeOn) {
     case radio:
       if (stateVehiculeOnRadio == StateVehiculeOnRadio.Null) {setStateVehiculeOnRadio(StateVehiculeOnRadio.radioOn);msg+=("BZZZZ BZZZZ the radio is up and running!");}break;
     case cameraDrill:if (stateVehiculeOnCameraDrill == StateVehiculeOnCameraDrill.Null) {setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.cameraDrillOn);msg += ("the camera & drill are up and running");}break;
    default:break;}  }
      private void exitStateVehiculeOnRadio() {
        switch (stateVehiculeOnRadio) {
        case radioOn:setStateVehiculeOnRadio(StateVehiculeOnRadio.Null);msg+=("the radio is live and on the air.");break;
        case connectToBase:setStateVehiculeOnRadio(StateVehiculeOnRadio.Null); msg += (" the radio is connected to the base.");break;
        case connectToNASAHeadquarters:setStateVehiculeOnRadio(StateVehiculeOnRadio.Null);msg += (" the radio is connected to NASA headquarters.");break;
        case disconnectToCommunication:setStateVehiculeOnRadio(StateVehiculeOnRadio.Null);msg += (" the radio is disconnected and offline.");break;
        default:break;}}

      private void setStateVehiculeOnRadio(StateVehiculeOnRadio aStateVehiculeOnRadio) {stateVehiculeOnRadio = aStateVehiculeOnRadio;if (stateVehiculeOn != StateVehiculeOn.radio && aStateVehiculeOnRadio != StateVehiculeOnRadio.Null) {setStateVehiculeOn(StateVehiculeOn.radio);}}
      private void exitStateVehiculeOnCameraDrill() {
        switch (stateVehiculeOnCameraDrill) {
        case cameraDrillOn:setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.Null);msg += (" the camera is up and working!");break;
        case activate16mmCamera:setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.Null);msg += (" the camera has activated its 16mm camera function.");break;
        case activateTheMoonSeries:setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.Null);msg += (" the camera has activated its Moon Series camera function. \nSmile!");break;
        case activateColorCamera:setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.Null);msg += (" the camera has activated its color camera function.");break;
        case takePicture:setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.Null);msg += (" **Click** **Click** the camera has taken pictures.");break;
        case activateDrill:setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill.Null);msg += (" the Drill is activated.");break;
        default:break;}}
      private void setStateVehiculeOnCameraDrill(StateVehiculeOnCameraDrill aStateVehiculeOnCameraDrill) {stateVehiculeOnCameraDrill = aStateVehiculeOnCameraDrill;if (stateVehiculeOn != StateVehiculeOn.cameraDrill&& aStateVehiculeOnCameraDrill != StateVehiculeOnCameraDrill.Null) {setStateVehiculeOn(StateVehiculeOn.cameraDrill);}}
}