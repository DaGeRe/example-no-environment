package de.example;

import java.io.IOException;
import java.io.Serializable;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import jenkins.tasks.SimpleBuildStep;

public class DoStuffBuilder extends Builder implements SimpleBuildStep, Serializable {

   private static final long serialVersionUID = -7455227251645979702L;

   @DataBoundConstructor
   public DoStuffBuilder() {
   }

   @Override
   public void perform(final Run<?, ?> run, final FilePath workspace, final Launcher launcher, final TaskListener listener) throws InterruptedException, IOException {
      listener.getLogger().println("Username via environment: " + run.getEnvironment(listener).get("GITUSER"));
      listener.getLogger().println("Username: " + System.getenv("GITUSER"));
   }

   private String getJobName(final Run<?, ?> run) {
      return run.getParent().getFullDisplayName();
   }

   @Symbol("doStuff")
   @Extension
   public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
      @Override
      public boolean isApplicable(final Class<? extends AbstractProject> aClass) {
         return true;
      }

      @Override
      public String getDisplayName() {
         return "example";
      }
   }
}
