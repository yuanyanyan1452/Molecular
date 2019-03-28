//package com.molecular.controller;
//
//import com.molecular.entity.Login;
//import com.molecular.entity.User;
//import com.molecular.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private VenueService venueService;
//
//    @Autowired
//    private PlanService planService;
//
//    @Autowired
//    private OrderService orderService;
//
//    /**
//     * @param request
//     * @param response
//     * @return 登录验证
//     * @throws Exception
//     */
//    @RequestMapping("/login")
//    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
//        String userId = request.getParameter("userId");
//        String password = request.getParameter("password");
//        if (userId.equals("molecular") && password.equals("heimao")){
//            Login tickets = new Login(userId,"Tickets");
//            request.getSession().setAttribute("tickets",tickets);
//            response.getWriter().print("molecular");
//        }else if (userId.length() == 7){
//            Login venue = venueService.login(userId,password);
//            if(venue != null){
//                request.getSession().setAttribute("venue",venue);
//                response.getWriter().print("venue");
//            }else {
//                response.getWriter().print("登录识别码或密码错误");
//            }
//        }else if(userId.length() >=8){
//            Login user = userService.login(userId,password);
//            if(user != null){
//                request.getSession().setAttribute("user",user);
//                response.getWriter().print("user");
//            }else {
//                response.getWriter().print("登录识别码或密码错误");
//            }
//        }else {
//            response.getWriter().print("登录识别码不存在");
//        }
//    }
//
//    /**
//     * 登出
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/logout")
//    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        String auth = request.getParameter("auth");
//        request.getSession().removeAttribute(auth);
//        response.getWriter().print("success");
//    }
//
//    /**
//     * 注册发送邮箱验证码
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/sendCode")
//    public void sendCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        String toMail = request.getParameter("toMail");
//        String code = userService.sendCode(toMail);
//        if (code != null && !code.equals("")){
//            response.getWriter().print(code);
//        }else {
//            response.getWriter().print("fail");
//        }
//    }
//
//    /**
//     * 用户注册
//     * @param request
//     * @param response
//     * @param attr
//     * @return
//     */
//    @RequestMapping(value = "/register",method = RequestMethod.POST)
//    public void register(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) throws Exception {
////        ModelAndView modelAndView = new ModelAndView("ReturnCode");
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String mail = request.getParameter("mail");
//        String userId = userService.register(username,password,mail);
//        if (userId != null && !userId.equals("")){
//            response.getWriter().print(userId);
//        }else {
//            response.getWriter().print("fail");
//        }
//    }
//
//    @RequestMapping("/returnCode")
//    public String ReturnCode(){
//        return "ReturnCode";
//    }
//
//    /**
//     * 加入登录验证的首页
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/index")
//    public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
//        ModelAndView modelAndView = new ModelAndView("../index");
//        if(request.getSession().getAttribute("molecular") != null){
//            modelAndView.addObject("auth","molecular");
//            modelAndView.addObject("tickets",request.getSession().getAttribute("molecular"));
//        }else if (request.getSession().getAttribute("venue") != null){
//            modelAndView.addObject("auth","venue");
//            modelAndView.addObject("venue",request.getSession().getAttribute("venue"));
//        }else if (request.getSession().getAttribute("user") != null){
//            modelAndView.addObject("auth","user");
//            modelAndView.addObject("user",request.getSession().getAttribute("user"));
//        }else{
//            modelAndView.addObject("auth","none");
//        }
//
//        List<Plan> planList = planService.getUnduePlan();
//        modelAndView.addObject("planList",planList);
//        return modelAndView;
//    }
//
//    /**
//     * 用户购票界面
//     * @param request
//     * @param planId
//     * @return
//     */
//    @RequestMapping("/buyPage")
//    public ModelAndView buyTickets(HttpServletRequest request,String planId){
//        ModelAndView modelAndView = new ModelAndView("../views/BuyTickets");
//        Plan plan = planService.getPlan(Integer.parseInt(planId));
//        modelAndView.addObject("plan",plan);
//        User userInfo = userService.getUserInfo(((Login)request.getSession().getAttribute("user")).getId());
//        modelAndView.addObject("userInfo",userInfo);
//        return modelAndView;
//    }
//
//    /**
//     * 下单
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/generateOrder")
//    public void generateOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        int planId = Integer.parseInt(request.getParameter("planId"));
//        String userId = request.getParameter("userId");
//        String venueId = request.getParameter("venueId");
//        String tickets = request.getParameter("molecular");
//        String status = request.getParameter("status");
//        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
//        Date startTime = new Date();
//
//        System.out.println("planId:"+planId+" userId:"+userId+" venueId:"+venueId+" molecular:"+tickets+" status:"+status+" totalPrice:"+totalPrice+" startTime:"+startTime);
//
//        Order order = new Order();
//        order.setPlanId(planId);
//        order.setUserId(userId);
//        order.setVenueId(venueId);
//        order.setTickets(tickets);
//        order.setStatus(status);
//        order.setTotalPrice(totalPrice);
//        order.setStartTime(startTime);
//        int orderId = orderService.addOrder(order);
//
//        if(orderId == -1){
//            response.getWriter().print("fail");
//        }else{
//            response.getWriter().print(orderId);
//        }
//
//    }
//
//    /**
//     * 支付页面
//     * @param request
//     * @param orderId
//     * @return
//     */
//    @RequestMapping("/payPage")
//    public ModelAndView payPage(HttpServletRequest request,String orderId){
//        ModelAndView modelAndView = new ModelAndView("../views/Pay");
//        Order order = orderService.load(Integer.parseInt(orderId));
//        modelAndView.addObject("order",order);
//        return modelAndView;
//    }
//
//    /**
//     * 取消订单
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/cancelOrder")
//    public void cancelOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        int orderId = Integer.parseInt(request.getParameter("orderId"));
//        orderService.cancelOrder(orderId);
//        response.getWriter().print("success");
//    }
//
//    /**
//     * 订单支付
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/pay")
//    public void pay(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        int orderId = Integer.parseInt(request.getParameter("orderId"));
//        String userId = request.getParameter("userId");
//        orderService.payOrder(orderId,userId);
//        response.getWriter().print("success");
//    }
//
//    /**
//     * 个人信息界面
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/personalInfoPage")
//    public ModelAndView personalInfoPage(HttpServletRequest request,HttpServletResponse response){
//        ModelAndView modelAndView = new ModelAndView("../views/PersonalInfo");
//        Login user = (Login)request.getSession().getAttribute("user");
//        User userInfo = userService.getUserInfo(user.getId());
//        modelAndView.addObject("userInfo",userInfo);
//        List<Order> orderList = orderService.getMyOrder(user.getId());
//
//        modelAndView.addObject("myOrderList",orderList);
//        List<Plan> planList = new LinkedList<Plan>();
//        for(int i=0;i<orderList.size();i++){
//            Order order = orderList.get(i);
//            Plan plan = planService.getPlan(order.getPlanId());
//            planList.add(plan);
//        }
//        modelAndView.addObject("planList",planList);
//        for(int i=0;i<planList.size();i++){
//            System.out.print(planList.get(i).getId()+" ");
//        }
//        return modelAndView;
//    }
//
//    /**
//     * 会员取消资格
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/cancelUser")
//    public void cancelUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        String userId = request.getParameter("userId");
//        userService.cancelUser(userId);
//        response.getWriter().print("success");
//    }
//
//    /**
//     * 修改用户信息
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/modifyInfo")
//    public void modifyInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        String userId = request.getParameter("userId");
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//        String mail = request.getParameter("mail");
//
//        User user = userService.getUserInfo(userId);
//        user.setUsername(userName);
//        user.setPassword(password);
//        user.setMail(mail);
//        if(userService.modifyUser(user)){
//            response.getWriter().print("success");
//        }else{
//            response.getWriter().print("修改信息失败，请稍候再试");
//        }
//    }
//
//    /**
//     * 退订
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/drawback")
//    public void drawback(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        int orderId = Integer.parseInt(request.getParameter("orderId"));
//        String userId = request.getParameter("userId");
//        System.out.println(orderId+" "+userId);
//        if(orderService.unsubscribe(orderId,userId)){
//            response.getWriter().print("success");
//        }else{
//            response.getWriter().print("退订失败");
//        }
//    }
//
//}
