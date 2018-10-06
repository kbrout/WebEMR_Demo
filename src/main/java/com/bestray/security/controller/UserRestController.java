package com.bestray.security.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bestray.model.security.Authority;
import com.bestray.model.security.Permission;
import com.bestray.model.security.User;
import com.bestray.security.JwtAuthenticationRequest;
import com.bestray.security.JwtTokenUtil;
import com.bestray.security.JwtUser;
import com.bestray.security.repository.AuthorityRepository;
import com.bestray.security.repository.PermisssionRepository;
import com.bestray.security.repository.UserRepository;
import com.bestray.security.service.JwtUserDetailsService;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PermisssionRepository permissionRepository;
    
    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request){

   // System.out.println("test"+SecurityContextHolder.getContext().getAuthentication().getPrincipal());
      JwtUser jwtuser=(JwtUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      System.out.println(jwtuser.getFirstname());
      String token = request.getHeader(tokenHeader).substring(7);
      String username = jwtTokenUtil.getUsernameFromToken(token);
      JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
      System.out.println("Authorities "+user.getAuthorities());
      System.out.println("toString>>"+user.toString());
      
   // Permission permission=permissionRepository.findByName("ADMIN_PANEL");
   //  System.out.println("permission "+permission.getName()+" "+permission.getId());
      Authority authority=authorityRepository.findByName("ROLE_USER");
     // System.out.println("permission "+authority.toString());
      List<Permission> list = authority.getPermission();
      for (int i = 0; i < list.size(); i++){
  		System.out.println(list.get(i));
  	}
      return user;
    }
    
    
@RequestMapping(value = "${jwt.route.registration.path}", method = RequestMethod.POST)
	
	public Response userreg(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException, RegExceptionHandler{

      //  authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        System.out.println("inside user rest controller "+authenticationRequest.getFirstname()+" "+authenticationRequest.getLastname()+" "+authenticationRequest.getPassword()+" "+authenticationRequest.getEmail());
          User user=new User();
          user.setFirstname(authenticationRequest.getFirstname());
          user.setLastname(authenticationRequest.getLastname());
          user.setUsername(authenticationRequest.getUsername());
          user.setPassword(authenticationRequest.getPassword());
          user.setEmail(authenticationRequest.getEmail());
    	//  try {
		   ((JwtUserDetailsService)userDetailsService).saveUser(user);
			return Response.ok().entity("Registration success !!").build();
	
	    //	return Response.ok("There is already a user registered with the email provided").status(400).build();
		//throw new RegError("unsuccess");
			
			
		
		//} catch (Exception e) {
		//    return  new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
		//}
       // System.out.println("done");
    	//final String token = jwtTokenUtil.generateToken(userDetails);
       // return ResponseEntity.ok(new JwtAuthenticationResponse(token));
     //  return  new ResponseEntity<Success>(HttpStatus.OK);
       //  return new ResponseEntity(new Success("msg"), null);
      //  return  new ResponseEntity<Success>(HttpStatus.OK);
      // return ResponseEntity.
      //  return Response.ok().entity("Registration success !!").build();
    }
    
   /* @RequestMapping(value="/registration",method = RequestMethod.GET)
    public ModelAndView showRegForm(){
    	return new ModelAndView("index");
    }
    */
    /*@RequestMapping(value = "/result", method=RequestMethod.GET)
	public ModelAndView showForm(){
        return new ModelAndView("result");
    }*/
    
    
    
    
    /*@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView saveAuthenticatedUser(@Valid User user, BindingResult bindingResult ) {
    	ModelAndView modelAndView = new ModelAndView();
    	User userExists = userRepository.findByEmail(user.getEmail());
    	if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
    	user.setLastPasswordResetDate(new Date());
    	user.setEnabled(true);
		if (bindingResult.hasErrors()){
			modelAndView.setViewName("index");
		} else {
    	
    		
      ((JwtUserDetailsService) userDetailsService).saveUser(user);
        modelAndView.addObject("successMessage", "User has been registered successfully");
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("index");
    }
		return modelAndView;
    }*/
    
    /*   @RequestMapping(value = "auth/registration", method = RequestMethod.POST)
    public JwtUser saveAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
       String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
      */
 /*   @RequestMapping(value = "auth/registration", method = RequestMethod.POST)
    //  public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request,@RequestBody JwtAuthenticationRequest authenticationRequest) {
    public ModelAndView processRegistrationForm( @ModelAttribute("user") User user,
    	      BindingResult result, ModelMap model ,@RequestParam("username") String username ) {
          ModelAndView modelAndView=new ModelAndView();
    //	String token = request.getHeader(tokenHeader).substring(7);
    //    String username = jwtTokenUtil.getUsernameFromToken(token);
         
          
    	JwtUser userExists = (JwtUser) userDetailsService.loadUserByUsername(username);
    		if (userExists != null) {
    			modelAndView.addObject("alreadyRegisteredMessage", " There is already a user registered with the name provided.");
    			modelAndView.setViewName("index");
    			
    		}
    			
    		 else { 
    		      
    		    // Generate random 36-character string token for confirmation link
    		    
    		        
    		  String str=  ((JwtUserDetailsService) userDetailsService).saveUser(user);
    				
    			
    			modelAndView.addObject("confirmationMessage", str);
    			modelAndView.setViewName("index");
    		}
    			
    		return modelAndView;
    	}
        */
    


@RequestMapping(value = "${jwt.route.userupdate.path}", method = RequestMethod.PUT)

public Response EditUser(@PathVariable("name")String name, @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException, ExceptionHandler_update{

  //  authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
      System.out.println("inside useredit rest controller "+authenticationRequest.getFirstname()+" "+authenticationRequest.getLastname()+" "+authenticationRequest.getPassword()+" "+authenticationRequest.getEmail());
     // Long uid=Long.parseLong(id);
     // System.out.println("id "+uid);
      User user=new User();
      user.setFirstname(authenticationRequest.getFirstname());
      user.setLastname(authenticationRequest.getLastname());
      user.setUsername(authenticationRequest.getUsername());
      user.setPassword(authenticationRequest.getPassword());
      user.setEmail(authenticationRequest.getEmail());

	
	   ((JwtUserDetailsService)userDetailsService).editUser(user,name);
		return Response.ok().entity("Update success !!").build();
}

@RequestMapping(value = "${jwt.route.userdelete.path}", method = RequestMethod.DELETE)
public Response DeleteUser(@PathVariable("name")String name) throws AuthenticationException, ExceptionHandler_delete{
	   ((JwtUserDetailsService)userDetailsService).deleteUser(name);
		return Response.ok().entity("Delete success !!").build();
}




 
}

    
    


